class Anagram

  attr_accessor :word

  def initialize word
    @word = word
    @anagrams = []
  end

  def match(matches)
    base = sort(@word.downcase)
    matches.each do |word|
      if !duplicate?(word, base)
        if sort(word.downcase) == base
          @anagrams << word
        end
      end
    end
    @anagrams.uniq
  end

  def sort(word)
    word.chars.sort.join
  end

  def duplicate?(word, base)
    word.downcase == @word
  end

end
