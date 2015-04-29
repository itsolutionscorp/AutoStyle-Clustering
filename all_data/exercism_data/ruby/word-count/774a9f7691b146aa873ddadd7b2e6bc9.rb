#!/usr/bin/ruby

class Phrase
  def initialize(input)
    @input = strip_punctuation input
    @input.downcase!
    @input = @input.split(" ")
  end

  def word_count
    count_hash = Hash.new(0)
    @input.each do |word|
      if count_hash.key? word
        count_hash[word] = count_hash[word] + 1
      else
        count_hash[word] = 1
      end
    end
    
    count_hash
  end

  def strip_punctuation(words)
    words.gsub(/\W+/, " ")
  end
end
