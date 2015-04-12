class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = Hash.new(0)
    clean_words.each do |clean_word|
      words[clean_word.to_s] += 1
    end

    return words
  end

  private

  def clean_words
    phrase.scan(/\w+/).collect { |word| word.downcase }
  end
end
