class Anagram

  def initialize(word)
    @original = word
    @target = canonicalize(normalize(word))
  end

  def match(wordlist)
    wordlist.select do |item|
      sorted_item = canonicalize(normalize(item))
      sorted_item == @target and normalize(item) != normalize(@original)
    end
  end

  private

  def normalize(str)
    str.to_s.downcase
  end

  def canonicalize(str)
    str.chars.sort
  end

end
