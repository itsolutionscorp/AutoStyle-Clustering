# author: patsu12
# exercise: word-count
# takes a string as input and generates a frequency count of words contained.

class Phrase

  def initialize(phrase)
    # my attempt at a regex to match chars needed to get the tests passing,
    # suggestions for improvement are welcome
    @phrase = phrase.gsub(/[^a-zA-z'\s\d)]|[\^]/, ' ').downcase.split(' ')
    count = ""
    self.word_count
  end

  def word_count
    count = Hash.new(0)
    @phrase.each { |word| count[word] += 1 }
    count
  end
end
