class Anagram
  def initialize(word)
    @word = word
  end

  def match(word_list)
    anagrams = []
    word_list.each do |word|
      if @word.downcase.scan(/\w/).sort == word.downcase.scan(/\w/).sort && @word.downcase != word.downcase
        anagrams << word
      end
    end
    anagrams
  end
end
