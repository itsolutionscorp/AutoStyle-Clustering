#!/bin/ruby

class Phrase < String

  def word_count
    result = Hash.new
    self.split(/[ ,:]/).each do |word|
      word = word.downcase.gsub( /[^[:alnum:]]/, '')
      next if word == ''
      result[word] ||= 0
      result[word] = result[word] +1
    end
    result
  end

end
