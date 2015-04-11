Anagram = Struct.new(:word) do

  def match(candidates)
    candidates.keep_if {|candidate| matches_word?(candidate)}
  end

  private
    def alphabetize(word)
      word.downcase.chars.sort
    end

    def sorted_word
      @sorted_word ||= alphabetize(word)
    end

    def matches_word?(candidate)
      alphabetize(candidate) == sorted_word
    end
end
