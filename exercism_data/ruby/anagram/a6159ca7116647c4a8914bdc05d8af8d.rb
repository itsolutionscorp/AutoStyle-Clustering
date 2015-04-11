class Anagram
  def initialize(word)
    @word = word.downcase
    @character_counts = character_counts(@word)
  end
  
  def match(possible_anagrams)
    possible_anagrams.select { |possible_anagram| anagram?(possible_anagram) } || []
  end

  private

  def character_counts(word)
    word.chars.each_with_object(Hash.new(0)) do |character, counts|
      counts[character] += 1
    end
  end

  def anagram?(other_word)
    other_word.downcase!
    (@word != other_word) && (@character_counts == character_counts(other_word))
  end
end
