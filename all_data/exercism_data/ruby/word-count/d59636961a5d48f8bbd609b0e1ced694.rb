class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(words)
  end
  
  private
  module Splitter
    def self.words(phrase)
      phrase.downcase.scan(/\w+/)
    end
  end

  def words
    Splitter.words(@phrase)
  end
  
  def count(words)
    words.each_with_object(Hash.new(0)) { |word, counts|
      counts[word] += 1
    }
  end
end
