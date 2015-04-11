class Anagram
  def initialize(word)
    @word = word
    @anagrams = []
  end

  def match(words)
    words.map do |x|
      if format(x) == format(@word) && x.downcase != @word.downcase
        @anagrams << x
      end
    end
    return @anagrams
  end

  def format(word)
    word.downcase.split("").sort.join
  end
end
