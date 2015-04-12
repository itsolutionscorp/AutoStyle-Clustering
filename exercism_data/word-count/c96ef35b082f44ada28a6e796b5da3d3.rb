require 'pp'

class Phrase
  
  attr_accessor :phrase
  
  def initialize(phrase)
    self.phrase = phrase
  end
  
  def word_count
    words = self.phrase.split(/\W/)
    counts = {}
    words.each do |word|
      word.downcase!
      unless word.empty?
        if counts[word]
          counts[word] += 1
        else
          counts[word] = 1
        end
      end
    end
    counts
  end
end
