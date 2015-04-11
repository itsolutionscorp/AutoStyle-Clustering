class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.new(word_stream).tallies
  end

  def word_stream
    by_words = /\w+/
    @phrase.enum_for(:scan, by_words)
  end
end

class WordCounter
  def initialize(words)
    @words = words
  end

  def tallies
    @tallies = Hash.new(0)
    @words.each {|word| count(word) }
    @tallies
  end

  private

  def count(word)
    canonical_word = word.downcase
    @tallies[canonical_word] += 1
  end
end
