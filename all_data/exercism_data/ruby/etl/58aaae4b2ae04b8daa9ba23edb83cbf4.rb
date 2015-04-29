class ETL
  def self.transform(input)
    input.each_with_object({}) do |(num, words), new_scores|
      words.each { |word| new_scores[word.downcase] = num }
    end
  end
end
