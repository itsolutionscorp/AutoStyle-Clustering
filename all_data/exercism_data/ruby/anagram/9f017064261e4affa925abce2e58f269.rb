class String
  def sort
    chars.sort.join
  end
  def anagram? s
    self != s and sort == s.sort
  end
end

class Anagram
  def initialize (str)
    @str = str.downcase
  end
  def match (ss)
    ss.select{|s| @str.anagram?(s.downcase)}
  end
end
