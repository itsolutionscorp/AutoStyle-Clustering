class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    parse_phrase.each_with_object({}) do |word, words|
      words[word] ||= 0
      words[word] += 1
    end
  end

  private

  def parse_phrase
    words = phrase.scan(/(\w*)/).map { |word| normalize_word(word.first) }
    words.delete_if { |word| word.empty? }
  end

  def normalize_word(word)
    word.strip.downcase
  end
end
