module ETL
  def self.transform(score_to_letters)
    score_to_letters.each_with_object({}) do |(score, letters), result|
      letters.each { |letter| result[letter.downcase] = score }
    end
  end
end
