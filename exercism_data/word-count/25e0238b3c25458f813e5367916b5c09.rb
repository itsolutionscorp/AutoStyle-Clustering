#!/usr/bin/ruby

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    message = prep @input
    count_hash = Hash.new(0)
    message.each do |word|
      count_hash[word] = count_hash[word] + 1
    end
    
    count_hash
  end

  def prep(words)
    words = strip_punctuation words
    words.downcase!
    words.split(" ")
  end

  def strip_punctuation(words)
    words.gsub(/\W+/, " ")
  end
end
