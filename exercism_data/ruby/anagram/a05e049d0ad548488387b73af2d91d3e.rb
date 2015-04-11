class Anagram

  def initialize(origin)
    @origin = origin
    @origin_chars = sorted_chars(origin)
  end

  def match(test_array)
    test_array.select { |w| sorted_chars(w) == @origin_chars && w.downcase != @origin.downcase }
  end

  private
  def sorted_chars(s)
    s.downcase.chars.sort
  end

end
