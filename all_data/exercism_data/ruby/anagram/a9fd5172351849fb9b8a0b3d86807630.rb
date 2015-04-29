Anagram = Struct.new :letters do
  def match(word_list)
    word_list.select { |word|
      anagram?(word)
    }
  end

  private
  def anagram?(word)
    letters.sum == word.sum
  end
end
