class ETL

  def self.transform(score_map)
    score_map.inject({}) do |accum, (score, words)|
      words.each do |word|
        accum[word.downcase] = score
      end
      accum
    end
  end

end
