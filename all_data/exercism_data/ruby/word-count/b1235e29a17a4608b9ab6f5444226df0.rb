class Phrase
  attr_reader :words, :word_count

  def initialize(input)
    @words = normalize_words input
    @word_count = Hash.new(0)
    count_words!
  end

  private

  def normalize_words(input)
    input.downcase.scan(/\w+/).flatten
  end

  def count_words!
    words.each { |word| word_count[word] += 1 }
  end
end
