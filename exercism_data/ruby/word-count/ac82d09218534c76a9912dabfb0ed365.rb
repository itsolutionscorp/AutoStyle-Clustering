#!/usr/bin/ruby

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    
    counts
  end

  def words
    @input.downcase.split(/\W+/)
  end
end