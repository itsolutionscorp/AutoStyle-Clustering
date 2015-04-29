str = "String"
p "sorting String:", str.chars.sort_by(&:downcase).join

# input:
input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#output = ["creams", "scream"]          t
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  anagrams = []
  groupHash = {}    # group already added
  words.each_with_index do |word, index|
    group = []
    w1 = word.chars.sort_by(&:downcase).join.downcase
    if groupHash[w1] == nil
      groupHash[w1] = []
    end
    groupHash[w1] << word
  end

  result = []
  groupHash.each_value do |value|
     result << value
  end
  result

end

def combine_anagramsx(words)
  anagrams = []
  groupHash = {}    # group already added
  words.each_with_index do |word, index|
    group = []
    w1 = ""

    words.each_with_index do |compareWord, compareIndex|

      #p word, '===', compareWord
      #p str.chars.sort_by(&:downcase).join

      w1 = word.chars.sort_by(&:downcase).join.downcase
      w2 = compareWord.chars.sort_by(&:downcase).join.downcase
      #w1 = word.unpack("c*").sort.pack("c*")
      #w2 = compareWord.unpack("c*").sort.pack("c*")
   #  p  print "\n", w1, " compare to ", w2
      if  w1 == w2 and index != compareIndex and groupHash[w1] == nil
        group = group + [compareWord]

        print "new group ", group, "\n"
      end
      if group.length == 0  and groupHash[w1] == nil and index != compareIndex
        group = group + [word]
      end
    end
    groupHash[w1] = 1

    if group.length > 0
     # p "adding to annagrams",  anagrams, group
      anagrams = anagrams + [group]
    end
  #  p "added to annagrams",  anagrams
  end

  anagrams
end

p combine_anagrams(input)
input1 = ["a", "b", "c", "d", 'a', 'd']
input2 = ['cars', 'cars', 'racs', 'scar'].sort + ['four', 'for', 'potatoes'] + ['creams', 'scream', 'scream'].sort
input3a = ['pots', 'spot', 'stop', 'tops', 'tops'].sort
input3b =  ['spots', 'stops'].sort <<  "sausage"
input3 = input3a + input3b
input4 = ['A', 'a'].sort
input5 = ['A', 'a', 'B', 'c', 'B']
input6 = ['HeLLo', 'hello'].sort
input7 =  ['Dog','dog','cat', 'CAT' ]

p "Failed test cases, 70% score, 82% next attempt after fixing case sensitivity"
p input2

#p  input1, combine_anagrams(input1)
#p  input2, combine_anagrams(input2)
#p  input3, combine_anagrams(input3)
#p  input4, combine_anagrams(input4)
#p  input5, combine_anagrams(input5)
#p  input6, combine_anagrams(input6)
#p  input7, combine_anagrams(input7)

input4 = ['a', 'a', 'a', 'A'].sort + ['b', 'b'] +  ['c'] +  ['D', 'd']
p "retest"

p  input1, combine_anagrams(input1)
p  input2, combine_anagrams(input2)
p  input3, combine_anagrams(input3)
p  input4, combine_anagrams(input4)     # missing extra "b"
