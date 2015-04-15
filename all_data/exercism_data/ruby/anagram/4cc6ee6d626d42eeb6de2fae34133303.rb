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
    needle.split('').sort == haystack.split('').sort
  end
end
