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
    candidates.select {|candidate| anagram?(candidate) }
  end

  private
    def fingerprint_match? candidate
      Word.new(@word).fingerprint == Word.new(candidate).fingerprint 
    end

    def exact_match? candidate
      @word.downcase == candidate.downcase
    end

    def anagram? candidate
      fingerprint_match?(candidate) and not exact_match?(candidate)
    end
end
