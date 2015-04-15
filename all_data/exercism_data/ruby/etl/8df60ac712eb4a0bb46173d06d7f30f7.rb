module ETL

	def self.transform(old_hash)
		hash = {}
		old_hash.each do |key, value|
			value.each do |letter|
				hash[letter.downcase] = key
			end
		end
		hash
	end
end
