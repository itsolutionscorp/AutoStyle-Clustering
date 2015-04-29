class ETL 
  def self.transform(old_hash)
    old_hash.each_with_object(Hash.new(0)) {
      |(key, values), new_hash| values.each {
        |value| new_hash.store(value.downcase, key)
      }
    }
  end
end
