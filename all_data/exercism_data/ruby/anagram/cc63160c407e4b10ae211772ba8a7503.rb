class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(word_list)
    anagrams = []
    word_list.each do |word|
      if prep(@anagram) == prep(word) && @anagram.downcase != word.downcase
        anagrams << word
      end
    end
    anagrams
  end

  private
  def prep(word)
    word.downcase.scan(/\w/).sort
  end
end
