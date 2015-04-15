class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(anagrams)
    w_anagrams = Array.new
    anagrams.each do |w|
      w = w.downcase
      w_anagrams << w if valid_size(w, @word) && valid_sum(w, @word) && valid_chars(w, @word)
    end
    w_anagrams
  end

  def word_ascii_sum(word)
    word.each_byte.inject(0, :+)
  end

  def valid_sum(w1, w2)
    word_ascii_sum(w1) == word_ascii_sum(w2) ? true : false
  end

  def valid_size(w1, w2)
    w1.size == w2.size ? true : false
  end

  def valid_chars(w1, w2)
    v = true
    w1.each_char do |l|
      v = false if !w2.include?(l)
    end
    v
  end
end
