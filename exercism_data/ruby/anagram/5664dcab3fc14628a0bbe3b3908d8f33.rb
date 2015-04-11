class Anagram
    attr_reader :word

  def initialize(word)
    @word = word
    @word_sorted = sort_chars word
  end

  def match(anagram_list)
    anagram_list.keep_if { |test_anagram| (test_anagram.downcase != @word.downcase) &&  (sort_chars( test_anagram ) == @word_sorted) }
  end

  private
  def sort_chars( word )
    word.downcase.chars.sort.join
  end

end
