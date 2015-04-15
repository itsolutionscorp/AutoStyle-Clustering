class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = letters_in(@word)
  end

  def match(anagrams)
    anagrams.select do |candidate|
      match_one(candidate.downcase)
    end
  end

  private

    def match_one(candidate)
      return false if candidate == @word
      return false if candidate.size != @word.size

      candidate_letters = letters_in(candidate)
      @letters.all?{ |letter, count| candidate_letters[letter] == count }
    end

    def letters_in(word)
      word.chars.reduce(Hash.new(0)) do |hash, letter|
        hash[letter] += 1
        hash
      end
    end
end
