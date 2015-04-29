class Anagram < String
  def match(words)
    words.select do |word|
      detect_anagram(word)
    end
  end

  private

  def detect_anagram(word)
    chars.sort == word.chars.sort
  end
end
