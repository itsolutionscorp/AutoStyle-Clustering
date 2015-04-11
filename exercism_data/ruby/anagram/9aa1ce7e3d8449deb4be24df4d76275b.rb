class Anagram < Struct.new(:word)
  def match(words)
    words.map { |word| Anagram.new(word) }
         .reject { |anagram| anagram.downcase == downcase }
         .select { |anagram| anagram.chars == chars }
         .map(&:word)
  end

  def chars
    word.downcase.chars.sort
  end

  def downcase
    word.downcase
  end
end
