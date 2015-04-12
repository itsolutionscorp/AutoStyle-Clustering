class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= tallies
  end

  private

  def tallies
    word_stream = @phrase.enum_for(:scan, /\w+/)
    counter = WordCounter.new(word_stream)
    counter.tallies
  end
end

class WordCounter
  def initialize(words)
    @words = words
    @tallies = Hash.new(0)
  end

  def tallies
    @words.each {|word| count(word) }
    @tallies
  end

  private

  def count(word)
    canonical_word = word.downcase
    @tallies[canonical_word] += 1
  end
end
