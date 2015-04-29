module Word 
  def letters(word)
    word.chars.sort
  end
end

class Anagram
  include Word

  def initialize(word)
    @letters = letters(word)
  end

  def match(words)
    words.select { |w| anagram?(w) }
  end

  private

  def anagram?(word)
    @letters == letters(word)
  end
end
