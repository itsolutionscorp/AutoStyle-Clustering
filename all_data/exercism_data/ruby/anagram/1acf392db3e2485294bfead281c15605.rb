require "unicode_utils"

class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    sorted = downcase(@word).chars.sort
    words.select do |anagram|
      sorted == downcase(anagram).chars.sort && 
        downcase(anagram) != downcase(@word)
    end
  end

  def downcase(str)
    UnicodeUtils.downcase(str)
  end
end
