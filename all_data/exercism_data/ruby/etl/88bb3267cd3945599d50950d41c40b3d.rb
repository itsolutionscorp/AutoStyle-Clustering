class ETL
  def self.transform scores
    new_scores = {}
    scores.each do |score,letters|
      letters.each do |letter|
        new_scores[letter.downcase] = score
      end
    end

    new_scores
  end
end
