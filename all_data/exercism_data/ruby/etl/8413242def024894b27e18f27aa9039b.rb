class ETL
  class << self

    def transform(old)
      transform_scores(old)
    end

    private

    def transform_scores(scores)
      scores.values.each_with_object({}) do |value, split_scores|
        value.each do |word|
          split_scores[word.downcase] = scores.key(value)
        end
      end
    end

  end
end
