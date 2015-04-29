class ETL
	def self.transform(h)
		new_hash = {}
		h.each do |key, val|
			val.each { |e| new_hash[e.downcase] = key }
		end
		new_hash
	end
end
