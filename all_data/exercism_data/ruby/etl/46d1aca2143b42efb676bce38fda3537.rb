class ETL 
  def self.transform(old_hash)
    new_hash = {}
    old_hash.each do |key, values|
      values.each do |value|
        new_hash.store(value.downcase, key)
      end
    end
    
    new_hash
  end
end
