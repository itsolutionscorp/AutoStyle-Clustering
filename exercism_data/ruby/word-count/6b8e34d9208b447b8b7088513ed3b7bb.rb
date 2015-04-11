class Phrase
  def initialize(sentence)
    @words = sentence.split(/\W+/).map {|word| Word.new(word) }
  end
  
  def word_count
    counter = WordCounts.new
    
    words.each { |word| counter.increment(word) }
    counter.to_hash
  end
  
  private
  attr_reader :words
end

class Word
  def initialize(word)
    @word = word.downcase
  end
  
  def countable?
    @word.length > 0
  end
  
  def to_s
    @word
  end
end

class WordCounts
  attr_reader :tally
  
  def initialize
    @tally = Hash.new(0)
  end
  
  def increment(word)
    return unless word.countable?
    
    tally[word.to_s] = tally[word.to_s] + 1
  end
  
  def to_hash
    tally
  end
end
