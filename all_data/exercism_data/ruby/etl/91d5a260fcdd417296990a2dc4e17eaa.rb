class ETL

  def self.transform(input)
    new(input).transform
  end

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def transform
    input.each_with_object({}) do |(num, words), new_scores|
      words.each { |word| new_scores[word.downcase] = num }
    end
  end
end
