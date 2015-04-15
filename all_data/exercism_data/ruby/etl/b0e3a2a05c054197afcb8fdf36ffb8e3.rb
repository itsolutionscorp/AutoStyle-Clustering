class ETL
	def self.transform(old)
		old.each_with_object({}) do |(score, letters), result| 
			letters.each { |l| result[l.downcase] = score } 
		end
	end
end
