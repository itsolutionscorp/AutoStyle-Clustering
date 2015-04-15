class ETL
	def self.transform(hash)
		result = Hash.new
		hash.each do |key,value|
			value.each do |v|
				result[v.downcase] = key
			end
		end
		result
	end
end
