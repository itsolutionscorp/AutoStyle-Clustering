class Phrase
  attr_reader :word_count
  def initialize(phrase)
    @word_count = Hash.new(0)
    @phrase = phrase
    normalize_phrase
    parse_phrase
  end

  private

  def normalize_phrase
    @phrase.downcase!
  end

  def parse_phrase
    @phrase.scan(/\w+/).each do |word|
      @word_count[word] += 1
    end
  end
end
