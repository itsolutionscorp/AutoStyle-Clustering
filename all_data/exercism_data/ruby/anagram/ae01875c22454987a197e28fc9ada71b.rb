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
    words.group_by { |word| same_letters?(@sorted_word, word) }
  end

  def same_letters?(worda, wordb)
    worda.downcase == sort_word(wordb.downcase) unless same_word?(wordb)
  end

  def sort_word(word)
    word.chars.sort.join ''
  end

  def same_word?(word)
    @word == word.downcase
  end

end
