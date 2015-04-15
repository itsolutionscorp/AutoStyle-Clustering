class ETL
  def self.transform(old)
    hash = {}
    old.each do|key,val|
      val.each { |v| hash[v.downcase] = key }
    end
    hash  
  end
end
