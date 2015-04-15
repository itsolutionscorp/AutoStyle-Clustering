class Anagram
  def initialize(needle)
    @needle = needle
  end
  attr_reader :needle

  def match(haystack)
    haystack.select do |candidate|
      anagram?(candidate)
    end
  end

  def anagram?(candidate)
    sort(candidate) == sort(needle)
  end

  def sort(str)
    str.chars.sort.join
  end
  private :sort
end
