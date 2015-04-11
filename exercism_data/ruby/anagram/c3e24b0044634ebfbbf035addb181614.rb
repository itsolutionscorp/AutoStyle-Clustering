class Anagram < String
  def match(words)
    words.select { |word| word.chars.sort == chars.sort }
  end
end
