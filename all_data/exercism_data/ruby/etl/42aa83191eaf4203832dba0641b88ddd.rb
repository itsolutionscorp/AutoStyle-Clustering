class ETL

  def self.transform(input)
    new(input).transform
  end

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def transform
    input.keys.inject({}) do |new, num|
      add_words_to_hash(new, num)
    end
  end

  private

  def add_words_to_hash(new, num)
    input[num].each_with_object(new) do |word, new|
      new[word.downcase] = num
    end
  end

end
