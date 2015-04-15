class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.scan(/(\w*)/).each_with_object({}) do |word, words|
      normalized_word = normalize_word word.first
      next if normalized_word.empty?

      words[normalized_word] ||= 0
      words[normalized_word] += 1
    end
  end

  private

  def normalize_word(word)
    word.strip.downcase
  end
end
