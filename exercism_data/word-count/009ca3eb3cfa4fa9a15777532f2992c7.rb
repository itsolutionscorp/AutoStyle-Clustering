class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_count = {}

    words = phrase.split /\s|:|,/
    words.each do |word|
      normalized_word = normalize_word word
      next if normalized_word.empty?

      words_count[normalized_word] ||= 0
      words_count[normalized_word] += 1
    end

    words_count
  end

  private

  def normalize_word(word)
    cleaned_word = word.match(/(\w*)/).captures.first

    cleaned_word.strip.downcase
  end
end
