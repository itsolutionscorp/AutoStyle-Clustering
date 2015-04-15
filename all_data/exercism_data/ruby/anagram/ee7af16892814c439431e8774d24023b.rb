class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| anagram(@word) == anagram(candidate) }
  end

  private
  
  def anagram(word)
    word.downcase.chars.sort
  end

end
