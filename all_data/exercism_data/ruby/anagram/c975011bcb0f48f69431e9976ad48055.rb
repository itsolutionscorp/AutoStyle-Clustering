class Anagram < Struct.new(:word)
  def match(words)
    words.find_all do |candidate|
      same_letters?(candidate)
    end
  end

  private

  def same_letters?(other)
    alphabetize_characters(word) == alphabetize_characters(other)
  end

  def alphabetize_characters(term)
    term.chars.sort
  end
end
