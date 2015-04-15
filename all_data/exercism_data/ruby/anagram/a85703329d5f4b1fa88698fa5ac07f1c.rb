class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select{ |candidate| anagram?(candidate) }
  end


  private

  def anagram?(candidate)
    return false if candidate.downcase == @word.downcase
    sorted_characters(candidate) == sorted_characters(@word)
  end

  def sorted_characters(word)
    word.downcase.chars.sort
  end

end
