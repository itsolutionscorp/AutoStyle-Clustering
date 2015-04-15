#!/bin/ruby

class Phrase < String

  def word_count
    result = Hash.new(0)
    self.scan(/\w+/)  {|word| result[word.downcase] += 1 }
    result
  end

end
