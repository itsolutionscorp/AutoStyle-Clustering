class Anagram 

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match( word_list )
    word_list.find_all { |word| anagram?(word) }
  end

  private

  def anagram?(anagram)

    return false if same? anagram
    
    sort_by_chars(word) == sort_by_chars(anagram)

  end

  def same?(anagram)
    word.upcase == anagram.upcase
  end

  def sort_by_chars( to_sort )
    to_sort.downcase.chars.sort
  end

end
