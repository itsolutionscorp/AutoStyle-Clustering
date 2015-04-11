class Anagram < Struct.new :base

  def match(words)
    words.select { |word| anagram? word }
  end

  private
  def anagram?(word)
    letters(base) == letters(word)
  end

  def letters(text)
    text.downcase.chars.sort
  end
end
