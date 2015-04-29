class Anagram < Struct.new(:word)

  def match(entries)
    entries.select { |e| anagram?(e) }
  end

  def anagram?(other_word)
    same_letters?(other_word) && !same_word?(other_word)
  end

  private 

  def same_word?(other_word)
    word.downcase == other_word.downcase
  end

  def same_letters?(other_word)
    word_chars = to_sorted_chars(word)
    other_word_chars = to_sorted_chars(other_word)
    word_chars.sort == other_word_chars.sort
  end
  
  def to_sorted_chars(string)
    string.downcase.chars.to_a.sort
  end
end
