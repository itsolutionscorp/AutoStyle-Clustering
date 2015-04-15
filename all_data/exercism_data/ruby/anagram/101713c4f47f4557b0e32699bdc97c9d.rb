class Anagram < String
  def =~(word)
    letters == Anagram.new(word).letters
  end

  def letters
    split("").sort
  end

  def match(words)
    words.select { |word| self =~ word }
  end
end
