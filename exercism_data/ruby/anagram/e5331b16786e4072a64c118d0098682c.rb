Anagram = Struct.new(:word) do 
  
  def match possible_anagrams
    word.downcase!
    possible_anagrams.find_all  do |possible_anagram|
      lowercase_anagram = possible_anagram.downcase
      lowercase_anagram != word && lowercase_anagram.split(//).sort == word.split(//).sort
    end
  end
  
end
