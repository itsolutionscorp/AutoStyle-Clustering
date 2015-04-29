class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    lowercase_words_in_phrase.each do |word|
      result[word] += 1
    end

    result
  end

private

  def words_in_phrase(phrase)
    phrase.scan(/\w+/)
  end

  def lowercase_words_in_phrase
    words_in_phrase phrase.downcase
  end

end
