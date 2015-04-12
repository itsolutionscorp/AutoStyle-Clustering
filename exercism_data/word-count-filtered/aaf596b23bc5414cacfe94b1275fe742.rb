# class to count words in a sentence using various rules
class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/[^\w']/).delete_if(&:empty?)
    words.each_with_object(Hash.new(0)) { |word, res| res[word.downcase] += 1 }
  end
end
