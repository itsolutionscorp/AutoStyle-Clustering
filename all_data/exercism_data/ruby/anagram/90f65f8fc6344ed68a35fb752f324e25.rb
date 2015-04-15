class Anagram < Struct.new(:word)
  def match(words)
    Array(words).find_all do |list_word|
      same_letters?(word, list_word)
    end
  end

  private

  def same_letters?(word, other)
    chars = word.chars.sort
    other_chars = other.chars.sort
    chars == other_chars
  end
end
