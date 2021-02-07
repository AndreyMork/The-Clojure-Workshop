(def memory-db (atom {}))

(defn read-db [] @memory-db)

(defn write-db [new-db] (reset! memory-db new-db))

(defn create-table
  [table-name]
  (let [
        empty-table {:data [] :indexes {}}
        db (read-db)
        updated-db (assoc db table-name empty-table)]
    (write-db updated-db)))

(defn drop-table
  [table-name]
  (let [
        db (read-db)
        updated-db (dissoc db table-name)]
    (write-db updated-db)))

(defn insert
  [table-name record id-key]
  (let [
        db (read-db)
        id-value (record id-key)]
    (if (some? (select-*-where table-name id-key id-value))
      (print
       (str "Record with " id-key ": " id-value " already exists. Aborting"))
      (let [
            updated-db (update-in db [table-name :data] conj record)
            data-index (dec (count (get-in updated-db [table-name :data])))]
        (write-db
         (assoc-in updated-db [table-name :indexes id-key id-value] data-index))))))

(defn select-*
  [table-name]
  (get-in (read-db) [table-name :data]))

(defn select-*-where
  [table-name field field-value]
  (let [
        table ((read-db) table-name)
        data-index (get-in table [:indexes field field-value])]
    (get-in table [:data data-index])))
