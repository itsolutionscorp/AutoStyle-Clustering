class Anagram

  def initialize word
    @letters = letters word
  end

  def match list
    list.select { |word| anagram? word }
  end

  private

  def anagram? word
    @letters == letters(word)
  end

  def letters word
    word.downcase.chars.sort
  end
end
