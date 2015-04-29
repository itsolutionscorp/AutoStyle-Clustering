class Anagram < String
  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
  end

  def anagram?(candidate)
    split('').sort == candidate.split('').sort
  end
end
