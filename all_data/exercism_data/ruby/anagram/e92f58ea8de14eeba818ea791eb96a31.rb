class Anagram < Struct.new(:word)
  def match(words)
    words.reject { |word| word.downcase == self.downcase }
         .select { |word| Anagram.new(word).letters == self.letters }
  end

  def letters
    word.downcase.chars.sort
  end

  def downcase
    word.downcase
  end
end
