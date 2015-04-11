class ETL
	def self.transform(hash)
		result = Hash.new
		hash.each do |k,v|
			v.each do |v|
				result[v.downcase] = k
			end
		end
		result
	end
end
