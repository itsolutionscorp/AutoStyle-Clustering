class Anagram

  def initialize(input)
    @anagram_matchee = input
  end

  def match(possible_anagrams)
    possible_anagrams.select{ |word| is_anagram(word) }
  end

  private
  def is_anagram(word)
    return false if word.downcase == @anagram_matchee.downcase
    @anagram_matchee.downcase.chars.sort.eql?(word.downcase.chars.sort)
  end

end
