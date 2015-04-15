class Anagram
  def initialize match_word
    @match_word = match_word
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  def anagram?(word)
    character_histogram(@match_word) == character_histogram(word)
  end

  def character_histogram(word)
    word.downcase.chars.sort
  end
end
