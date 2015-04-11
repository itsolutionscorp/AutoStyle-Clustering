class Anagram
  def initialize(value)
    @string_value = value.to_s.downcase
  end

  def match(possible_matches)
    possible_matches.each_with_object([]) do |word, matches|
      if anagram?(word.downcase)
        matches.push(word)
      end
    end
  end

  private
  def anagram?(possible_anagram)
    @string_value != possible_anagram &&
        @string_value.chars.sort == possible_anagram.chars.sort
  end
end
