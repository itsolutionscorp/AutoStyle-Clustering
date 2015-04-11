class ETL
  
  def self.transform old_scores
    old_scores.each.with_object({}) do |(score, words), new_scores|
      words.each do |word|
        new_scores[word.downcase] = score
      end
    end
  end
  
end
