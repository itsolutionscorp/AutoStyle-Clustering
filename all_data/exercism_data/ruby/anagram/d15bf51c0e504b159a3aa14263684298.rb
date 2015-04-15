class Anagram
  def initialize(word)
    @word = word
    @letters = word.downcase.scan(/[\w]/).sort
  end
  def match(anagrams)
    match = []
    for i in anagrams
      for j in i.scan(/[\w]+/).sort
        if j.downcase == @word.downcase
          next
        end
        k = j.downcase.scan(/[\w]/).sort
        if @letters == k
          match.push j
        end
      end
    end   
    return match
  end  
end
