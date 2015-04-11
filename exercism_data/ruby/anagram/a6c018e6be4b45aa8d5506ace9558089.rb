class Anagram
  def initialize(sentence)
    @sentence = sentence
  end
  
  def match(searched_words=[])
    words = @sentence.downcase.split(" ")
    anagrams = []
    for word in words do
      for search in searched_words do
        s = search.downcase
        anagrams << search if is_anagram?(word, s)
      end
    end
    anagrams
  end
  
  private
  def is_anagram?(word, s)
    word != s && word.split('').sort == s.split('').sort
  end
end
