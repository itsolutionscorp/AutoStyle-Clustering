class Phrase
  WORDS = /\w+/ # http://rubular.com/r/ZDkwtMfFlt

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(words_from(@phrase))
  end

  private

  def count(words)
    word_count = Hash.new(0)
    words.each { |word| word_count[word.downcase] += 1 }
    word_count
  end

  def words_from(phrase)
    phrase.scan(WORDS)
  end
end
