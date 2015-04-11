class ETL
  def self.transform(data)
    scrabble_scores = {}

    data.keys.length.times { |i|
      data.values[i].length.times { |j|
        scrabble_scores.merge!(data.values[i][j].downcase => data.keys[i])
      }
    }

    scrabble_scores

  end
end
