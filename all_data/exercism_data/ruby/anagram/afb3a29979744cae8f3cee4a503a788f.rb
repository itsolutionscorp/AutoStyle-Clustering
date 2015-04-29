class Anagram
  def initialize match_word
    @character_count_to_match = character_count match_word
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  def anagram?(word)
    @character_count_to_match == character_count(word)
  end

  def character_count(word)
    word.downcase.chars.each_with_object(Hash.new(0)) do |char, counts|
      counts[char] = counts[char] + 1
    end
  end
end
