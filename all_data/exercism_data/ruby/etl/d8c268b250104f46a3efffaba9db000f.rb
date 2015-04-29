class ETL
  def self.transform(score)
    score.each_with_object({}) do |(key, val), new_score|
      val.each do |word|
        new_score[word.downcase] = key
      end
    end
  end
end
