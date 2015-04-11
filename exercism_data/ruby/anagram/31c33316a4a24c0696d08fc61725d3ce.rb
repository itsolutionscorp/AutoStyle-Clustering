class Anagram
  def initialize word
    @word = word
  end

  def match possible_anagrams
    possible_anagrams.select {|word| anagram? word }
  end

  def anagram? word
    signature(word) == my_signature
  end

  private

  def signature word
    word.downcase.chars.sort
  end

  def my_signature
    @signature ||= signature(@word)
  end
end
