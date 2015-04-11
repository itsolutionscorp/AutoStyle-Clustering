class ETL
	def self.transform (old)
		nuevo = Hash.new()
		old.each do |key, value|
			value.each do |n|
				nuevo[n.downcase]=key
			end
		end
		return nuevo
	end
end
