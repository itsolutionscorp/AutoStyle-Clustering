class Anagram
  def initialize(string)
    @sorted_anagram = normalized(string)
  end

  def match(list)
    list.select {|x| normalized(x) == @sorted_anagram }
  end

  private
  def normalized(string)
    string.downcase.chars.sort
  end
end
