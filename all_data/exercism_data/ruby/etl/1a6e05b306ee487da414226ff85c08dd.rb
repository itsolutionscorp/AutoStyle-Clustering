module ETL
  def self.transform(legacy)
    legacy.each_with_object({}) do  |(score, letters), scores|
      letters.each { |letter| scores[letter.downcase] = score }
    end
  end
end
