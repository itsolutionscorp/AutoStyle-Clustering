class Phrase
  WORDS_REGEX = /[a-zA-Z0-9]+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    frequencies = Hash.new(0)

    words.each do |w|
      frequencies[w] += 1
    end

    frequencies
  end

  def words
    @phrase.downcase.scan(WORDS_REGEX)
  end
end
