class Phrase
  WORD_BOUNDARIES = /[, ]+/
  
  attr_reader :words
  
  def initialize(sentence)
    @words = sentence.split(WORD_BOUNDARIES).map {|word| Word.new(word) }
  end
  
  def word_count
    counter = WordCounter.new
    
    words.each { |word| counter.increment(word) }
    counter.to_hash
  end
end

class Word
  def initialize(word)
    @word = normalize(word)
  end
  
  def countable?
    @word.length > 0
  end
  
  def to_s
    @word
  end
  
  private
  
  def normalize(word)
    word.gsub(/\W+/, '').downcase
  end
end

class WordCounter
  attr_reader :tally
  
  def initialize
    @tally = Hash.new {|hash, key| hash[key] = 0 }
  end
  
  def increment(word)
    return unless word.countable?
    
    tally[word.to_s] = tally[word.to_s] + 1
  end
  
  def to_hash
    tally
  end
end
