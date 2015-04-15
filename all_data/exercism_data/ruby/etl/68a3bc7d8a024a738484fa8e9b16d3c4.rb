class ETL
  def self.transform(old_scores)
    old_scores.each_with_object({}) do |(score, letters), new_scores|
      letters.map { |letter| new_scores[letter.downcase] = score }
    end
  end
end
