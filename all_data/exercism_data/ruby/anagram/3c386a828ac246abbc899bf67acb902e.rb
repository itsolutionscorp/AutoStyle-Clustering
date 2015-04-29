class Anagram

  def initialize(word)
    @word = word
    @wc = word_count(word)
  end

  def match(words)
    words = words.reject { |word| word.upcase == @word.upcase }
    words = words.map { |word| word}.find_all { |word| word_count(word) == @wc }
    words.uniq { |word| word.upcase }
  end

  def word_count(word)
    wc = Hash.new(0)
    word.each_char { |char| wc[char.downcase] += 1 }
    wc
  end

end
