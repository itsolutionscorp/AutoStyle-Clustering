class Anagram < String
  def match(words)
    words.select{|w| eql?(w)}
  end

  def eql?(other)
    chars.sort == other.chars.sort
  end
end
