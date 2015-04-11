class Anagram
  def initialize(word)
    @word = word.downcase
    @chars = group_chars(@word)
  end

  def match(anagrams)
    anagrams.group_by{ |a| char_group_for(normalize(a)) }
            .fetch(chars){ [] }
  end

  private
  attr_reader :chars, :word

  def group_chars(word)
    word.chars.sort
  end

  def char_group_for(anagram)
    group_chars(anagram) unless anagram == word
  end

  def normalize(anagram)
    anagram.downcase
  end
end
