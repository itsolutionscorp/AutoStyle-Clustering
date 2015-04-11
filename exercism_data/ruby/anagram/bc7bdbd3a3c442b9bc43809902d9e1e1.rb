class Word
  def initialize word
    @word = word
  end

  def fingerprint
    @word.downcase.each_char.sort
  end
end


class Anagram
  def initialize word
    @word = word
  end

  def match candidates
    candidates.each_with_object([]){|candidate, matches|
      matches << candidate if anagram?(candidate)
    }
  end

  private
    def fingerprint_match? candidate
      Word.new(@word).fingerprint == Word.new(candidate).fingerprint 
    end

    def identical_word? candidate
      @word.downcase == candidate.downcase
    end

    def anagram? candidate
      fingerprint_match?(candidate) and not identical_word?(candidate)
    end
end
