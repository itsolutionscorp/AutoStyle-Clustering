class Anagram < String
  def match(candidates)
    candidates.select { |candidate| matches?(candidate) }
  end

  def matches?(candidate)
    split('').sort == candidate.split('').sort
  end
end
