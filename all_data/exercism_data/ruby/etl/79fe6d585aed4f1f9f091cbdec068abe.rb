class ETL

	def self.transform(old)
		result = Hash.new

		old.each do |key, value|
			value.each do |letter|
				result[letter.downcase] = key
			end
		end

		result
	end
end
