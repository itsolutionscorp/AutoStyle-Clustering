class ETL
  def self.transform(old_scores)
    {}.tap do |new_scores|
      old_scores.each do |score, letters|
        letters.each do |letter|
          new_scores[letter.downcase] = score
        end
      end
    end
  end
end
