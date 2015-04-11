Anagram = Struct.new(:word) do
  def match(possibles)
    possibles.select { |possible| anagrams?(word, possible) }
  end

  private
    def anagrams?(first, second)
      first.downcase != second.downcase && first.downcase.chars.sort == second.downcase.chars.sort
    end
end
