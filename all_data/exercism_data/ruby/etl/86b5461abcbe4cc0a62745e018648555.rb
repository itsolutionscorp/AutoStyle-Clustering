class ETL
	def self.transform(old)
		old.each_with_object({}) do |(value, letters), value_per_letter |
			letters.each { |letter|	 value_per_letter[letter.downcase] = value }
		end  
	end	
end
