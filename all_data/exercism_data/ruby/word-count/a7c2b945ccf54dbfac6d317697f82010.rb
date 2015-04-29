class Phrase
  WORDS = /\w+/ # http://rubular.com/r/ZDkwtMfFlt

  def initialize(phrase)
    @word_count = Hash.new(0)
    count(words_from(phrase))
  end

  def count(words)
    words.each { |word| @word_count[word.downcase] += 1 }
  end

  def word_count
    @word_count
  end

  private

  def words_from(phrase)
    phrase.scan(/\w+/)
  end
end
