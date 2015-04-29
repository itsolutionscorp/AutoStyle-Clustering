class Anagram

  def initialize(word)
    @word = word.downcase
    @word_characters = @word.chars.sort
  end

  def match(candidates)
    candidates.each_with_object([]) do |candidate, matches|
      matches << candidate if anagram?(candidate)
    end
  end


  private

  def anagram?(candidate)
    return false if candidate.downcase == @word
    candidate.downcase.chars.sort == @word_characters
  end

end
