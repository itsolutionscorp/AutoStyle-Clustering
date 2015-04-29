class ETL
	
	def ETL.transform(hash)
	
		hash = hash.invert
		result = {}
		hash.each do |key, value|
			key.each do |entry|
				p entry
				r = {entry.downcase! => value}
				result.merge!(r)
			end
		end

		result	
	end

end
