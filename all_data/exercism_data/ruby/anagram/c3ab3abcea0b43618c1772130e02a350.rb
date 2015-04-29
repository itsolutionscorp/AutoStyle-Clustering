class Anagram
  attr_reader :str

  def initialize(str)
    @str = str
  end

  def match(list)
    list.select { |s| str.downcase.chars.sort == s.downcase.chars.sort && str.downcase != s.downcase }
  end
end
