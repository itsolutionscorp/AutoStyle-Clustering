Anagram = Struct.new(:word) do

  def match(candidates)
    sorted_word = alphasort(word)
    candidates.keep_if { |c| alphasort(c) == sorted_word }
  end

  private
    def alphasort(word)
      word.downcase.split("").sort.join
    end
end
