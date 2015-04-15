Anagram = Struct.new(:word) do

  def match(candidates)
    candidates.keep_if {|candidate| anagram?(candidate)}
  end

  private
    def alphabetize(word)
      word.downcase.chars.sort
    end

    def sorted_word
      @sorted_word ||= alphabetize(word)
    end

    def anagram?(candidate)
      alphabetize(candidate) == sorted_word
    end
end
