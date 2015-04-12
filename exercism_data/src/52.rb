class Phrase
  def initialize(sentence)
    @words = sentence
  end

  def word_count
    @words.scan(/([\w']+)/).flatten.each_with_object(Hash.new(0)) { |word, hash|
      hash[word.downcase] += 1
    }
  end
end
