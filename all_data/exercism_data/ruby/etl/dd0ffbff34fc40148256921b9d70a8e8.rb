class ETL
  class << self

    def transform(list_scores)
      list_scores.each_with_object({}) do |(score, rack), point_scores|
        rack.each do |letter|
          point_scores[letter.downcase] = score
        end
      end
    end

  end
end
