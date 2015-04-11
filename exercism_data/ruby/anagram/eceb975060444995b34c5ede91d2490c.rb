class Anagram

  def initialize(word)
    @word = word
    @freqs = letter_freqs(word)
  end

  def match(words)
    words.find_all { |word| anagram?(word) }
  end

  def letter_freqs(word)
    word.each_char.with_object(Hash.new(0)) { |char, wc| wc[char.downcase] += 1 }
  end

  def anagram?(word)
    word.upcase != @word.upcase && letter_freqs(word) == @freqs
  end

end
