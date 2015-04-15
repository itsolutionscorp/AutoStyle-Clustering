class Anagram < Struct.new(:word)
  def match(words)
    words.reject { |word| word.downcase == downcase }
         .select { |word| Anagram.new(word).letters == letters }
  end

  def letters
    word.downcase.chars.sort
  end

  private

  def downcase
    word.downcase
  end
end
