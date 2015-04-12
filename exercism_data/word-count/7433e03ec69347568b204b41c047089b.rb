class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_stream = @phrase.enum_for(:scan, /\w+/)
    WordCounter.new(word_stream).tallies
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
