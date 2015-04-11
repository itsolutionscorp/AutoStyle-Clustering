class Anagram

  def initialize(word)
    @word = word
    @wc = word_count(word)
  end

  def match(words)
    words.reject { |word| word.upcase == @word.upcase }
    .find_all { |word| word_count(word) == @wc }
  end

  def word_count(word)
    word.each_char.with_object(Hash.new(0)) { |char, wc| wc[char.downcase] += 1 }
  end

end
