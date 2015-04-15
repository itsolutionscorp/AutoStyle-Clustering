module ETL
	def self.transform(old_hash)
		new_hash = {}
		old_hash.each do |key, value_arr|
			value_arr.each do |value|
				new_hash[value.downcase] = key
			end
		end
		p Hash[new_hash.sort]
	end
end
