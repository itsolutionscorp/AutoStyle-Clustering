class String
  def fingerprint
    downcase.chars.sort.join
  end
end

class Anagram
  attr_reader :word
  private :word

  def initialize(word)
    @word        = word
  end

  def match(anagrams)
    anagrams.select do |anagram|
      anagram.downcase != word.downcase && anagram.fingerprint == word.fingerprint
    end
  end
end
