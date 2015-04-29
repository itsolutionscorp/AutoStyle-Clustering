class ETL
  def self.transform(old_scores)
    old_scores.inject({}) do |new_scores, (score, letters)|
      letters.each { |letter| new_scores[letter.downcase] = score }
      new_scores
    end
  end
end
