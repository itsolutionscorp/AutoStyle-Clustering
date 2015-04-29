Anagram = Struct.new(:word) do

  def match(candidates)
    candidates.keep_if { |c| alphasort(c) == alphasort(word) }
  end

  private
    def alphasort(word)
      word.downcase.split("").sort.join
    end
end
