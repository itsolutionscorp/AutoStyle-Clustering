class Anagram < Struct.new(:word)
  def match(words)
    words.find_all do |list_word|
      same_letters?(list_word)
    end
  end

  private

  def same_letters?(other)
    alphabetize_characters(word) == alphabetize_characters(other)
  end

  def alphabetize_characters(string)
    string.chars.sort
  end
end
