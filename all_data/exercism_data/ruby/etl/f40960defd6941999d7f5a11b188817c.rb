class ETL
  def self.transform(old, new_hash = {})
    old.each do |k,v|
      v.each { |v| new_hash[v.downcase] = k }
    end
    
    new_hash
  end
end
