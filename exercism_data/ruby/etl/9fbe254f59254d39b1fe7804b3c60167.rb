class ETL

  def self.transform(input)
    new(input).transform
  end

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def transform
    hash = {}
    input.each do |num, words|
      words.each do |word|
        hash[word.downcase] = num
      end
    end
    hash
  end

end
