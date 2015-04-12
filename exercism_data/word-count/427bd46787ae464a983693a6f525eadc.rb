class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words = @sentence.scan(/[a-z0-9]+/i)
    result = Hash.new(0)
    words.each { |word| result[word.downcase] += 1 }
    result
  end
end
