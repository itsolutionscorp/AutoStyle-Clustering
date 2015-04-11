class ETL
  def self.transform(score)
    score.each_with_object({}) do |(points, letters), new_score|
      letters.each do |letter|
        new_score[letter.downcase] = points
      end
    end
  end
end
