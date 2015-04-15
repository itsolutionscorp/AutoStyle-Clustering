class ETL
	
	def self.transform(old)
		old.keys.each_with_object({}) do |value, hash|
			old[value].each do |letter|
				hash[letter.downcase] = value
			end
		end
	end

end
