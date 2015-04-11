class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    actual_anagrams = []
    possible_anagrams.each do |possible_anagram|
      if same_length?(possible_anagram) && same_letters?(possible_anagram) && same_word?(possible_anagram)
        actual_anagrams << possible_anagram
      end
    end
    actual_anagrams
  end

  private
  def same_length?(possible_anagram)
    @word.length == possible_anagram.length
  end

  def same_letters?(possible_anagram)
    @word = @word.downcase
    possible_anagram = possible_anagram.downcase
    @word.chars.sort == possible_anagram.chars.sort
  end

  def same_word?(possible_anagram)
    @word = @word.downcase
    possible_anagram = possible_anagram.downcase
    @word != possible_anagram
  end
end
