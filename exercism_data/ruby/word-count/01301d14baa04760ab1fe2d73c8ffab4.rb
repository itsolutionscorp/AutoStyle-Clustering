class Phrase
  attr_accessor :phrase

  def initialize(input)
    @phrase = input
  end

  def word_count
    create_histogram_from(
      tokenize(
        normalize))
  end

  private

  def create_histogram_from(tokens)
    tokens.each_with_object({}) { |t, hist|
      hist[t] = hist.fetch(t, 0) + 1
    }
  end

  def tokenize(str)
    str.gsub(/[^\w\s]/, " ").split
  end

  def normalize
    phrase.downcase
  end
end
