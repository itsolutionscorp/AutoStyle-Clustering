class Anagram
  def initialize(original_phrase)
    @original_fingerprint = anagram_fingerprint original_phrase
  end

  def match(possible_anagrams)
    possible_anagrams.select { |w| anagram_fingerprint(w) == @original_fingerprint }
  end

  private

  def anagram_fingerprint(phrase)
    phrase.downcase.chars.sort
  end
end
