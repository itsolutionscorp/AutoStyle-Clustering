Anagram = Struct.new(:word) do
  def match(possible_matches)
    possible_matches.select do |match|
      alphabetize(word) == alphabetize(match)
    end
  end

  def alphabetize(word)
    alphabetizations[word] ||= word.downcase.chars.sort.join
  end

  def alphabetizations
    @alphabetizations ||= {}
  end
end
