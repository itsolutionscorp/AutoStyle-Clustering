Anagram = Struct.new(:subject) do

  def match(potential_words)
    potential_words.select do |word|
      anagram_to_subject?(word)
    end
  end

private
  def anagram_to_subject?(word)
    alphabetize(word) == alphabetize(subject)
  end

  def alphabetize(word)
    word.chars.sort
  end
end
