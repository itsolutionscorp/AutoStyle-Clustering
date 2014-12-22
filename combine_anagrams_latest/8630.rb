# To change this template, choose Tools | Templates
# and open the template in the editor.

class Anagram
  def initialize(word)
    @contents= Array.new(1, word) 
    @master = word.downcase.chars.sort.join
  end

  def master
    @master
  end

  def is_match?(new_word)
    if new_word.downcase.chars.sort.join == @master
      return true
    else
      return false
    end
  end

  def add(new_word)
    @contents<<new_word
  end

  def contents
    @contents
  end

end

def test_anagram
  a = Anagram.new "hello"
  if a.is_match? "elhol"
    a.add("elhol")
  end
  puts a.contents
  return "end of test"
end

def combine_anagrams(words)

  anagrams = Array.new(1,Anagram.new(words[0]))
  i=1
  
  while i<words.length

    flag = true
    for j in 0..(anagrams.length-1)
#      if anagrams[j].is_match? words[i]
#        anagrams[j].add words[i]
#      else
#        anagrams << Anagram.new(words[i])
#        break
#      end
      if anagrams[j].is_match? words[i]
        flag = false
        anagrams[j].add words[i]
        break
      end
    end

    if flag == true
      anagrams << Anagram.new(words[i])
    end
    i=i+1
  end

  results = Array.new(1,anagrams[0].contents)

  anagrams.length
  i=1
  while i<anagrams.length
    results << anagrams[i].contents
    i=i+1
  end

  results
end
