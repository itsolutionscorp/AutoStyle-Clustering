class Anagram

  def initialize(input)
    @anagram_matchee = input.downcase
  end

  def match(possible_anagrams)
    possible_anagrams.delete_if{ |word| !is_anagram(word) }
  end

  private
  def is_anagram(word)
    @anagram_matchee.chars.sort.eql?(word.chars.sort)
  end

end
