class ETL
	def self.transform(old_hash)
		new_hash = {}
		old_hash.each do |key, value|
			value.each do |val|
				new_hash[val.downcase] = key
			end
		end
		new_hash
	end
end
