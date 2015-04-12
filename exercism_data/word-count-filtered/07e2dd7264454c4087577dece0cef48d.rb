require 'pry'

class Phrase
  def initialize(string)
    @input = string.downcase.strip.gsub( /[^\w\s]/, ' ' )
  end

  def word_count
    running_count = {}

    result  = @input.split
    result.each do |word|
      running_count[word] = result.count(word)
    end
    running_count
  end
end
