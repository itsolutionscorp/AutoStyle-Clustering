class Phrase
  
  def initialize(input)
    @input = input
  end

  def word_count
    WordsCounter.new(words).count
  end

  private

    attr_reader :input

    def words
      input.downcase.scan(words_pattern)
    end

    def words_pattern
      /\w+/   
    end

end

class WordsCounter
  
  attr_reader :words
  def initialize(words)
    @words = words
  end

  def count
    words.inject(Hash.new(0)) do |accum, word|
      accum[word] += 1
      accum
    end 
  end

end
