class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    lowercase_words_in_phrase.each_with_object({}) do |word, result|
      result[word] += 1
    end

  end

private

  def words_in_phrase(phrase)
    phrase.scan(/\w+/)
  end

  def lowercase_words_in_phrase
    words_in_phrase phrase.downcase
  end

end
