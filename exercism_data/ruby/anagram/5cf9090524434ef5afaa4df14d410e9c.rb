class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      (sort_and_downcase possible_anagram) == (sort_and_downcase @word)
    end 
  end

  private

  def sort_and_downcase(str)
    str.downcase.chars.sort
  end

end

# I tried to extract variables on line 11, but didn't like it so much.
# Still, not super happy with the use of parenthesis. Suggestions?
