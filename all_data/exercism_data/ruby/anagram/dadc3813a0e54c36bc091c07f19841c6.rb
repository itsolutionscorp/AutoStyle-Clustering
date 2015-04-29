class Anagram
  def initialize(word)
    @needle = fingerprint(word)
  end

  def match(haystack)
    haystack.select {|word| fingerprint(word) == @needle}
  end

  private

  def fingerprint(word)
    word.downcase.chars.sort
  end
end
