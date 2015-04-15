module ETL
  def self.transform(scores_by_rack)
    scores_by_rack.each_with_object({}) do |(score, rack), scores_by_tile|
      rack.each do |letter|
        scores_by_tile[letter.downcase] = score
      end
    end
  end
end
