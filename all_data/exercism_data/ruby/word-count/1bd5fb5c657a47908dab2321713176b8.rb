require 'delegate'

class Phrase
  def initialize(sentence)
    @words = sentence.split(/\W+/).map {|word| Word.new(word) }
  end
  
  def word_count
    counts = WordCounts.new
    
    words.each { |word| counts.increment(word) }
    counts
  end
  
  private
  attr_reader :words
end

class Word < SimpleDelegator
  def initialize(word)
    super(word.downcase)
  end
  
  def countable?
    length > 0
  end
  
  def eql?(other)
    self == other
  end
end

class WordCounts < SimpleDelegator
  def initialize
    super(Hash.new(0))
  end
  
  def increment(word)
    return unless word.countable?
    
    self[word] = self[word] + 1
  end
end
