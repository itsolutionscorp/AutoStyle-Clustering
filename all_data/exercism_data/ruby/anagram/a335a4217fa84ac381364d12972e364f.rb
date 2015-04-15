class Anagram

  attr_reader :str

  def initialize(str)
    @str = str
  end

  def match ary
    ary.select { |e| e.downcase != str.downcase && e.downcase.sort == str.downcase.sort }
  end

end

class String
  def sort
    chars.sort.join
  end
end
