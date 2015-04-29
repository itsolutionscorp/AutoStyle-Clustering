class Anagram

  def initialize(word)
    @sorted_word = sort_word(word.downcase)
    @word = word
  end

  def match(words)
    group_anagrams(words)[true] || []
  end

  private
  def group_anagrams(words)
    words.group_by { |word| is_anagram?(word) }
  end

  def is_anagram?(word)
    same_letters?(word) && !same_word?(word)
  end

  def same_letters?(word)
    @sorted_word.downcase == sort_word(word.downcase) 
  end

  def sort_word(word)
    word.chars.sort.join ''
  end

  def same_word?(word)
    @word == word.downcase
  end

end
