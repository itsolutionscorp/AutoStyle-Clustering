class Anagram
  def initialize word
    @dc_word = word.downcase
  end

  def match words
    words.select do |w|
      dc_w = w.downcase
      char_identical?(dc_w, @dc_word) && dc_w != @dc_word
    end
  end

  private
  def char_identical?(w1, w2)
    w1.char_sort == w2.char_sort
  end
end

class String
  def char_sort
    chars.sort.join
  end
end
