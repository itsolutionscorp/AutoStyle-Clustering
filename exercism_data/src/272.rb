require 'pry'
class Phrase
  def initialize(words)
    @words = words
  end
  
  def word_count
    @words.downcase!
    @words.gsub!(/[^\w\']/, " ")
    phrase = @words.split(" ")
    count = Hash.new(0)
    phrase.map! { |word| count[word] += 1 }
    count
  end
end
