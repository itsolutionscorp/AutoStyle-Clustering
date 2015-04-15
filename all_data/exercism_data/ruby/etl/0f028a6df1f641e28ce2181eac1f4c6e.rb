class ETL
  def self.transform(old_hash)
    new_hash = Hash.new
    old_hash.each do |key, value_array| 
      value_array.map {|v| new_hash.store(v.downcase, key)}
    end
    new_hash
  end
end
