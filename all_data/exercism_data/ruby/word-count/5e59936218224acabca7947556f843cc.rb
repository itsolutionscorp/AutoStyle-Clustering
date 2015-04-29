# author: patsu12
# exercise: word-count
# takes a string as input and generates a frequency count of words contained.

class Phrase

  def initialize(phrase)
    @phrase = phrase.gsub(/[^\w']/, ' ').downcase.split(' ')
    self.word_count
  end

  def word_count
    count = Hash.new(0)
    @phrase.each { |word| count[word] += 1 }
    count
  end
end
