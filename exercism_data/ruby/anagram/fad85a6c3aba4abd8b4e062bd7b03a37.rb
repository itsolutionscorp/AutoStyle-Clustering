class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.delete_if {|i| !anagram?(@word.downcase, i.downcase) }
  end

  private
  def anagram?(needle, haystack)
    return false if needle == haystack
    letter_count(needle).sort == letter_count(haystack).sort
  end

  def letter_count(s)
    s.split('').inject(Hash.new(0)) { |a, b| a[b] += 1; a }
  end
end
