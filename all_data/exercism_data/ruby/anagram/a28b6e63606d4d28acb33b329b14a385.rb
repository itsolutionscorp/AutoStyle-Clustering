class Anagram < Struct.new(:word)
  def match(words)
    Array(words).find_all do |list_word|
      same_letters?(list_word)
    end
  end

  private

  def same_letters?(other)
    alphabatize_charachters(word) == alphabatize_charachters(other)
  end

  def alphabatize_charachters(string)
    string.chars.sort
  end
end
