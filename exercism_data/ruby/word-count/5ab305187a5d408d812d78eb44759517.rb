#!/usr/bin/ruby

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words = words_split
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    
    counts
  end

  def words_split
    words = @input.downcase
    words.split(/\W+/)
  end
end
