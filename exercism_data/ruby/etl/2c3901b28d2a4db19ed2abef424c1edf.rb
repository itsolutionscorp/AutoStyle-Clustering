module ETL
  def self.transform(legacy)
    legacy.each_with_object({}) do  |value, scores|
      score, letters = value
      letters.each { |letter| scores[letter.downcase] = score }
    end
  end
end
