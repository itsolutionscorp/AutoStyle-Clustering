class ETL
	def self.transform (hash)
	 hash1 = Hash.new()
	  hash.each do |k,v|
	   v.each do |l| 
	   	hash1[l.downcase] = k 
	   end 
	end
	 return hash1
	  end
end
