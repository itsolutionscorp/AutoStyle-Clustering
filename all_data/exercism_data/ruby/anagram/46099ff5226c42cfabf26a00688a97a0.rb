class Anagram  
  def initialize(word)
    @word = word.downcase
    @chars = @word.chars.sort
  end
  
  def match(list)
    list.select {|item| anagram?(item.downcase)}
  end
  
  private
  def anagram?(other)
    !identical_words?(other) && chars_matches?(other)
  end
  
  def chars_matches?(other)
    (@chars <=> other.chars.sort) == 0
  end
  
  def identical_words?(other)
    @word.casecmp(other) == 0
  end
end
