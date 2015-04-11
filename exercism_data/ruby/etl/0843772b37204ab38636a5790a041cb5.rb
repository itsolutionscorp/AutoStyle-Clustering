class ETL
  def self.transform(old)
    new_hash = {}
    old.each do |old_key, v|
      v.each do |new_key| 
        new_hash[new_key.downcase] = old_key
      end
    end
    new_hash
  end
end
