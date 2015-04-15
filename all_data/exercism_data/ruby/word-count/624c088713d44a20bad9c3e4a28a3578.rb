class Phrase
  WORDS = /\w+/ # http://rubular.com/r/ZDkwtMfFlt

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(words_from(lowercased(@phrase)))
  end

  private

  def count(words)
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def words_from(phrase)
    phrase.scan(WORDS)
  end

  def lowercased(phrase)
    phrase.downcase
  end
end
