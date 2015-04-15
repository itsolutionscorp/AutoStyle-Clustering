class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.each_with_object([]) do |w, l|
      l << w if anagram?(w)
    end
  end

  private
  def anagram?(candidate)
    candidate = candidate.downcase
    different?(candidate) && same_characters?(candidate)
  end

  def different?(candidate)
    @word != candidate
  end

  def same_characters?(candidate)
    @word.chars.sort == candidate.chars.sort
  end
end
