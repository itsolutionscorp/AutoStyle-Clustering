class ETL

	def self.transform(old)
    old.each_with_object({}) do |(score, letters), newHash|
      letters.each do |letter|
        newHash[letter.downcase] = score
      end
    end
  end

end
