class Anagram
  def initialize(word)
    @original_word = word.to_s.downcase
  end

  def match(possible_matches)
    possible_matches.select{|word| anagram?(word.downcase)}
  end

  private
  def anagram?(possible_anagram)
    @original_word != possible_anagram &&
        @original_word.chars.sort == possible_anagram.chars.sort
  end
end
