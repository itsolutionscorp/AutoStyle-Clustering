class ETL
  def self.transform(scores)
    scores.each_with_object({}) do |(score, letters), new_scores|
      letters.each { |letter| new_scores[letter.downcase] = score }
    end
  end
end
