class Phrase
  def initialize(sentence)
    @words = sentence.downcase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
end
