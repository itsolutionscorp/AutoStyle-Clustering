class Phrase
  EXCEPT_ALPHABETS_AND_SINGLE_QUOTES = /[^\w']+/

  def initialize(phrase)
    @phrase = phrase.downcase

    @histogram = Hash.new(0)
    words.each { |word| @histogram[word] += 1 }
  end

  def word_count
    @histogram
  end

  private

  def words
    @phrase.split(EXCEPT_ALPHABETS_AND_SINGLE_QUOTES)
  end
end
