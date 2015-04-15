class Anagram < String
  def match(candidates)
    candidates.select { |candidate| matches?(candidate) }
  end

  def matches?(candidate)
    chars.sort == candidate.chars.sort
  end
end
