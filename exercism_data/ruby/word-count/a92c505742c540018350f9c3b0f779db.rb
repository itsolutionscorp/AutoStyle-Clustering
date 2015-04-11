class Phrase
  attr_accessor :phrase

  def initialize(input)
    @phrase = input
  end

  def word_count
    create_histogram_from(
      tokenize(
        normalize(phrase)))
  end

  private

  def create_histogram_from(tokens)
    tokens.each_with_object(Hash.new(0)) { |t, hist|
      hist[t] += 1
    }
  end

  def tokenize(input)
    input.gsub(/[^\w\s]+/, " ").split
  end

  def normalize(input)
    input.downcase
  end
end
