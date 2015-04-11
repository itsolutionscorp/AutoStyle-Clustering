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
    word.downcase.chars.to_a.sort == other_word.downcase.chars.to_a.sort
  end
end
