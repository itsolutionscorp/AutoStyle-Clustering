class ETL
  def self.transform old
    old.reduce(Hash.new) do |new_hash, (key,val)| 
      val.each {|value| new_hash[value.downcase] = key}
      new_hash
    end
  end
end
