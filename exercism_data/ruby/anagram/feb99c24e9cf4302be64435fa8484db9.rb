module Tweak

  def self.alphabetize(a_string)
    a_string.downcase.chars.sort.join
  end
end

class Anagram

  def initialize(word)
    @word = Tweak.alphabetize(word)
  end

  def match(text)
    text.select {|str| Tweak.alphabetize(str) == @word}
  end

end
