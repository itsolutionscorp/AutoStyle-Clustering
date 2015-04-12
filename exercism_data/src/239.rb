require 'pry'

class Phrase
  def initialize(string)
    @input = string.downcase.strip.gsub( /[^\w\s]/, ' ' )
  end

  def word_count
    running_count = {}

    result  = @input.split
    result.each do |word|
      unless running_count.has_key?
        running_count[word] = result.count(word)
      end
    end
    running_count
  end
end
