#!/usr/bin/ruby
# Word count exercise

class Phrase  
  def initialize(phrase)
    @phrase = phrase.downcase
    @counts = Hash.new(0)
    @done = false
  end
  
  def word_count
    process unless @done
    @counts
  end
  
  def process
    @phrase.scan(/[\w']+/).each do |word|
      @counts[word]+=1
    end
    @done=true
  end
end
