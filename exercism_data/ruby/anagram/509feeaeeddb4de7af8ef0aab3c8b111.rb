class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.each_with_object([]) do |candidate, matches|
      matches << candidate if anagram?(candidate)
    end
  end

  private

  def anagram?(candidate)
    same_letters?(candidate) && !same_word?(candidate)
  end

  def same_word?(candidate)
    candidate.downcase == @word.downcase
  end

  def same_letters?(candidate)
    rearrange_letters(candidate) == rearrange_letters(@word)
  end

  def rearrange_letters(word)
    word.downcase.split(//).sort.join
  end
end
