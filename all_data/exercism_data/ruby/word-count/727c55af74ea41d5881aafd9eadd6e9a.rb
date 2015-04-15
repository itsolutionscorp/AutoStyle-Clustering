# Author::    Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

# Determine word frequency in a phrase

class Phrase
  
  def initialize(input)
    @phrase = input.downcase
  end

  def word_count
    dict = Hash.new(0)
    words = @phrase.split(/\W+/)
    words.each { |word| dict[word] += 1 }
    dict
  end

end
