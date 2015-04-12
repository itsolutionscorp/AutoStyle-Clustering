class Phrase
  def initialize(words)
    @words = Hash.new(0)
    words.downcase.scan(/[\w']+/).each { |word| @words[word] += 1 }
  end

  def word_count
    @words
  end
end
