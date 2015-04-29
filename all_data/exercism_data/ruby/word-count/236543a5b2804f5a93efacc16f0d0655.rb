class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    clean_words.each_with_object(Hash.new(0)) do |clean_word, words|
      words[clean_word] += 1
    end
  end

  private

  def clean_words
    phrase.downcase.scan(/\w+/)
  end
end
