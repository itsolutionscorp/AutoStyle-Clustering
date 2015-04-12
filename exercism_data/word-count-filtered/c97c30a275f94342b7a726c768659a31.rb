# class to count words in a sentence using various rules
class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/[^\w']/).delete_if(&:empty?)
    words.reduce(Hash.new(0)) { |res, word| res[word.downcase] += 1; res}
  end
end
