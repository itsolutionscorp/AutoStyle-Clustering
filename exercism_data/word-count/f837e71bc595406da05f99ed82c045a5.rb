class Phrase
  def initialize(phrase)
    @word_count = count(phrase)
  end

  attr_reader :word_count

  private
  def count(input_string)
    frequency   = Hash.new(0)
    only_words  = /[\w\']+/

    string_normalized = input_string.downcase
    words_separated   = string_normalized.scan(only_words)

    words_separated.each do |word|
      frequency[word] += 1
    end

    frequency
  end
end
