class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words = @sentence.downcase.scan(/[a-z0-9]+/)
    result = Hash.new(0)
    words.each { |word| result[word] += 1 }
    result
  end
end
