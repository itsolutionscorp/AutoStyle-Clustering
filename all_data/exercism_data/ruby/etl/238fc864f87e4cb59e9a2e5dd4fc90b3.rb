module ETL
  def self.transform(scores_by_rack)
    scores_by_rack.each_with_object({}) do |(score, tiles), scores_by_tile|
      tiles.each do |tile|
        scores_by_tile[tile.downcase] = score
      end
    end
  end
end
