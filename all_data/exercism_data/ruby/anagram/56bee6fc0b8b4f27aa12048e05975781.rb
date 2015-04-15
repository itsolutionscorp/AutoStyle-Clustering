class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    anagrams = candidates.collect do |candidate|
      candidate if anagram?(candidate) && different?(candidate)
    end
    anagrams.compact
  end

  private

  def anagram?(candidate)
    sort(candidate) == sort(@word)
  end

  def different?(candidate)
    @word.downcase != candidate.downcase
  end

  def sort(word)
    word.downcase.split('').sort.join
  end
end
