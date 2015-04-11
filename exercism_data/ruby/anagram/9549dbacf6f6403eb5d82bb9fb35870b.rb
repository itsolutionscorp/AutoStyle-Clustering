class Anagram
  def initialize word
    @signature = signature(word)
  end

  def match possible_anagrams
    possible_anagrams.select {|word| is_anagram? word }
  end

  def is_anagram? word
    signature(word) == @signature
  end

  private

  def signature word
    word.downcase.chars.sort
  end
end
