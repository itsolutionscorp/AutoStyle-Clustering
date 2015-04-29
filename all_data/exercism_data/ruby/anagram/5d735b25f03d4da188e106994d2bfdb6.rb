class Anagram 

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match( word_list )
    key = sort_by_chars(word)
    word_list.find_all { |word| anagram?(key, word) }
  end

  private

  def anagram?(key, anagram)

    return false if same? anagram
    
    key == sort_by_chars(anagram)

  end

  def same?(anagram)
    word.upcase == anagram.upcase
  end

  def sort_by_chars( to_sort )
    to_sort.downcase.chars.sort
  end

end
