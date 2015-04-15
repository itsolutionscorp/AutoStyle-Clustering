class Anagram
  def initialize (word)
    @word = word
  end
  def match(candidates)
    anagrams = []
    candidates.each do |candidate|
      if candidate.downcase != @word
        if normalized(candidate)== normalized(@word)
          anagrams << candidate
        end
      end
    end
    anagrams
  end

  private
  def normalized(word)
    word.downcase.chars.sort
  end

end
