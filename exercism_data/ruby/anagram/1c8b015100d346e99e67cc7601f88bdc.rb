class Anagram
  def initialize(string)
    @sorted_anagram = string.normalize
  end

  def match(list)
    list.select {|x| x.normalize == @sorted_anagram }
  end
end

class String
  def normalize
    self.downcase.split('').sort
  end
end
