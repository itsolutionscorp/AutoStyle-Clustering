class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.inject(word_counter) {|word_counter, w| 
      word_counter.count_the_word w
    }.to_hash
  end

  def words
    @phrase.scan(/\w+/).map &:downcase
  end
   
  def word_counter
    WordCounter.new 
  end

  class WordCounter
    attr_reader :stats

    INITIAL_COUNT = 0

    def initialize 
      @stats = Hash.new {|h, some_word| h[some_word] = INITIAL_COUNT }
    end

    def count_the_word word
      stats[word] += 1
      self
    end

    def to_hash
      stats
    end
  end
end
