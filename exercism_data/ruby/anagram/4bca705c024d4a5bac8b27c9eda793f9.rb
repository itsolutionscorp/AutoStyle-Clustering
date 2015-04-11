class String
  def fingerprint
    downcase.chars.sort.join
  end
end

class Anagram
  attr_reader :word, :fingerprint
  private :word, :fingerprint

  def initialize(word)
    @word        = word
    @fingerprint = word.fingerprint
  end

  def match(anagrams)
    anagrams.select do |anagram|
      anagram.casecmp(word) != 0 && anagram.fingerprint == fingerprint
    end
  end
end
