Anagram = Struct.new(:word) do

  def match(candidates)
    anagram = Proc.new {|c| alphabetize(c) == sorted_word}
    candidates.keep_if &anagram
  end

  private
    def alphabetize(word)
      word.downcase.split("").sort
    end

    def sorted_word
      @sorted_word ||= alphabetize(word)
    end

end
