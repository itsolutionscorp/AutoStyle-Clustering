class Anagram
  def initialize(string)
    @sorted_anagram = string.downcase.split('').sort
  end

  def match(list)
    list.select {|x| x.downcase.split('').sort == @sorted_anagram }
  end
end
