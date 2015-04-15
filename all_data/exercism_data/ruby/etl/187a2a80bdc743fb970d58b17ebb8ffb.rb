class ETL
  
  def self.transform(old)
    new_hash = {}
    old.each do | k,v |
      v.each do |a|
        new_hash[a.downcase] = k
      end
    end  
    new_hash
  end
  
end    
