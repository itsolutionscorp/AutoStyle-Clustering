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
      input[num].each_with_object(new) do |word, new|
        new[word.downcase] = num
      end
    end
  end

end
