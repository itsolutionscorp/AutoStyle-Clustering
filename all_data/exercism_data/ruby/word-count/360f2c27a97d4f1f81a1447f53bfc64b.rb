class Phrase
  def initialize(string)
    @words = string.scan(/\w+/).map { |word| Word.new(word) }
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word.to_s] += 1
    end
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def to_s
    @word.downcase
  end
end
