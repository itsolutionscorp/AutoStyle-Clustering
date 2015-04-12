class Phrase
  def initialize(sentence)
    @words = cleanup(sentence).split(' ')
  end

  def cleanup(sentence)
    sentence.gsub(/\W+/, ' ').downcase
  end

  def word_count
    @words.reduce(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end
end
