class Anagram
  attr_reader :word
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select do |w| 
      word_hash == chars_hash(w) && word != w.downcase
    end
  end

  private

  def word_hash
    @word_hash ||= chars_hash(word)
  end

  def chars_hash(word)
    word.downcase.chars.each_with_object(Hash.new(0)) do |char, hash|
      hash[char] += 1
    end
  end
end
