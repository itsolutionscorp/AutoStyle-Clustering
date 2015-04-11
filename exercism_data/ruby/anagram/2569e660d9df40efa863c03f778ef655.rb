class Anagram

  def initialize(canonical_string)
    @canonical_string = normalize(canonical_string)
  end

  def match(possible_anagrams)
    possible_anagrams.select {|candidate|
      @canonical_string == normalize(candidate)
    }
  end

  private
  def normalize(string)
    string.downcase.chars.sort
  end
end
