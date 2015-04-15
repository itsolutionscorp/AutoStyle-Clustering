class Word
  def initialize word
    @word = word
  end

  def normalized
    @word.downcase.each_char.sort
  end
end


class Anagram
  def initialize word
    @word = word
  end

  def match candidates
    matches = []
    candidates.each{|candidate|
      matches << candidate if anagram?(candidate)
    }
    matches
  end

  private
    def normalized
      Word.new(@word).normalized
    end

    def identical_word? candidate
      @word.downcase == candidate.downcase
    end

    def anagram? candidate
      normalized == Word.new(candidate).normalized and not identical_word?(candidate)
    end
end
