class ETL
  def self.transform(old_scores)
    old_scores = old_scores.to_h
    new_scores = {}

    old_scores.each do |score, letters|
      letters.map { |letter| new_scores[letter.downcase] = score }
    end

    new_scores
  end
end
