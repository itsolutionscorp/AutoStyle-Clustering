#!/usr/bin/ruby
# Word count exercise

class Phrase  
  def initialize(phrase)
    @phrase = phrase
    @counts = Hash.new(0)
    @done = false
  end
  
  def word_count
    process unless @done
    @counts
  end
  
  def process
    @phrase.split(/[^\w']+/).each do |word|
      @counts[word.downcase]+=1
    end
    @done=true
  end
end
