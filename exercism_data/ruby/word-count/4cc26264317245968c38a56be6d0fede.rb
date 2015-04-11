class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase_words.each_with_object(Hash.new(0)) do |word, words|
      words[word] += 1
    end
  end

  private

  def phrase_words
    phrase.scan(/(\w+)/).map { |word| normalize_word(word.first) }
  end

  def normalize_word(word)
    word.downcase
  end
end
