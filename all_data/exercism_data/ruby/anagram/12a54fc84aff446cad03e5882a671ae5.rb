class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram| anagram?(possible_anagram.downcase) }
  end

  private

  def anagram?(possible_anagram)
    return false if @word == possible_anagram or @word.size != possible_anagram.size 
    return letters_are_the_same?(possible_anagram)
  end

  def letters_are_the_same?(possible_anagram)
    @word.chars.inject(possible_anagram) do |possible_anagram, letter|
      if possible_anagram.include?(letter)
        possible_anagram.sub(letter, "")
      else
        return false
      end
    end.empty?
  end
end
