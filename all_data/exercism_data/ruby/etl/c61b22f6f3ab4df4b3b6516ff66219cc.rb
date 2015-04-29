class ETL
	def self.transform(data)
		{}.tap do |result|
			data.each do |points, letters| 
				letters.each{|letter| result[letter.downcase] = points}
			end
		end
	end
end
