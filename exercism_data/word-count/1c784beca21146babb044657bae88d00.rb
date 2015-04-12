#!/usr/bin/ruby

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words = words_split @input
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    
    counts
  end

  def words_split(words)
    words = punctuation_ignored words
    words.downcase!
    words.split(" ")
  end

  def punctuation_ignored(words)
    words.gsub(/\W+/, " ")
  end
end
