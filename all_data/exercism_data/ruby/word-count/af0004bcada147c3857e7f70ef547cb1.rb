class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words = @sentence.downcase.gsub(/[^a-z0-9 ]/, ' ').split(' ')
    words.reduce(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end
end
