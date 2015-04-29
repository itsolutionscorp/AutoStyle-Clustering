class ETL

	def self.transform(letters)
		my_hash = Hash.new
		
		letters.each do |key, value|
			value.each do |char|
				my_hash[char.downcase] = key
			end
		end
		my_hash
	end


end
