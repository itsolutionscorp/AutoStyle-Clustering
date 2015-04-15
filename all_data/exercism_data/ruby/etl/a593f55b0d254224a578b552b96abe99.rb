class ETL
	def self.transform(input={})
		hash={}
		input.each do |key,value|	
			value.each do |v|
				hash[v.downcase()] = key
			end
		end
		return hash
	end
end
