# Author::    Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

# Determine word frequency in a phrase

class Phrase
  
  def initialize(input)
    @phrase = input.downcase
  end

  def word_count
    words = @phrase.split(/\W+/)
    word_freq = words.each_with_object(Hash.new(0)) { |word, freq| freq[word] += 1 }
  end

end
