class Anagram
  def initialize(word)
    @word = word.downcase
  end
  
  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram| anagram?(possible_anagram) } || []
  end

  private

  def anagram?(other_word)
    other_word.downcase!

    if @word == other_word
      false
    else
      @word.to_s.chars.sort == other_word.to_s.chars.sort
    end
  end
end
