class ETL

	def self.transform(old_hash)
		new_hash = {}
		old_hash.each do |key, value|
			value.each do |x|
				new_hash.store(x.downcase, key)
			end
		end
		return new_hash
	end

end
