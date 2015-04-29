module Tweak

  def self.downcase_sort(a_string)
    a_string.downcase.chars.sort.join
  end
end

class Anagram

  def initialize(word)
    @word = Tweak.downcase_sort(word)
  end

  def match(content)
    content.select {|elt| Tweak.downcase_sort(elt) == @word}
  end

end
