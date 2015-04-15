class ETL

	def self.transform(legacy)
		legacy.each_with_object({}) do |(score, letters), transformed|
			letters.each {|letter| transformed[letter.downcase]=score }
		end
	end

end
