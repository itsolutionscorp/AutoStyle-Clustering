class ETL
	def self.transform(data)
		results = {}
		data.each do |score, letters|
			letters.each { |letter| results[letter.downcase] = score }
		end
		results
	end
end
