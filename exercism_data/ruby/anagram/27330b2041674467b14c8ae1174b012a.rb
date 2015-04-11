class Anagram
  def initialize word
    @word = word
  end

  def match words
    words.select do |w|
      char_identical?(w, @word) && w.downcase != @word.downcase
    end
  end

  private
  def char_identical?(w1, w2)
    w1.downcase.char_sort == w2.downcase.char_sort
  end
end

class String
  def char_sort
    self.chars.sort.join
  end
end
