class ETL
  class << self

    def transform(scores)
      scores.each_with_object({}) do |(key,value), split_scores|
        value.each do |word|
          split_scores[word.downcase] = key
        end
      end
    end

  end
end
