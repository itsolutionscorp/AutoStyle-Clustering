class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.each_with_object(Array.new) do |w, anagrams|
      anagrams << w if is_anagram?(w.downcase)
    end
  end

  def is_anagram?(w)
    return false if word.eql?(w)

    unless w.length == word.length
      return false
    end

    word.scan(/\w/).sort == w.scan(/\w/).sort
  end
end
