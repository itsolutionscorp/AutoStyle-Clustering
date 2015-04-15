class Phrase
  EXCEPT_ALPHABETS_AND_SINGLE_QUOTES = /[^\w']+/

  attr_reader :word_count

  def initialize(phrase)
    @phrase = phrase.downcase

    @word_count = Hash.new(0)
    words.each { |word| @word_count[word] += 1 }
  end

  private

  def words
    @phrase.split(EXCEPT_ALPHABETS_AND_SINGLE_QUOTES)
  end
end
