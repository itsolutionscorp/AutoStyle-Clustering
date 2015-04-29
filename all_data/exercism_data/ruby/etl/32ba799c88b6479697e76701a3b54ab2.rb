class ETL
  class << self

    def transform(scores)
      scores.values.each_with_object({}) do |value, split_scores|
        value.each do |word|
          split_scores[word.downcase] = scores.key(value)
        end
      end
    end

  end
end
