class Phrase
  WORDS = /\w+/ # http://rubular.com/r/ZDkwtMfFlt

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    count(words_from_phrase)
  end

  private

  def count(words)
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def words_from_phrase
    @phrase.scan(WORDS)
  end
end
