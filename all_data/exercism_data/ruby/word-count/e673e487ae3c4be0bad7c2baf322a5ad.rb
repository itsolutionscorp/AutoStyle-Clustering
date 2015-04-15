#!/bin/ruby

require 'delegate'

class Phrase < SimpleDelegator

  def initialize( contents )
    super( contents )
  end

  def word_count
    result = Hash.new(0)
    words = split_by_words
    words.each { |word| result[word] +=1 }
    result
  end

  private
    def split_by_words
      result = Array.new
      self.downcase.scan(/\w+/) { |word| result << word } 
      result
    end

end
