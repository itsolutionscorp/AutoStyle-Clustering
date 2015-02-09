def combine_anagrams(words)
  seedArray = []
  wordArray = [] # change this to Hash if dup words are to be treated only once
  words.each {|word|
    seed = word.downcase.split(/[.]?/).sort.join # remove downcase to be canse sensitive
    # "Cars" ==> "cars" ==> "c" "a" "r" "s" ==> "a" "c" "r" "s" ==> "acrs"
    wordArray.push([word,seed]) # key is the original (cased) word
    seedArray.push(seed)
    # keep the seeds in a separate array.  Same seed appears
    # multiple times for anagrams
  }
  seedHash = {}
  seedArray.each { |seed| seedHash[seed] = seedArray.count(seed) }
  anagramArray = []
  seedHash.each {|seed1,count|
    anagram = [] # an array of anagram words
    wordArray.each {|word,seed2|
      if (seed1==seed2) then anagram.push(word) end
    }
    anagramArray.push(anagram) # an array of arrays
  }
  return anagramArray
end

print "\n\n__________ Part 3 __________\n\n"

words = ['apple', 'apple', 'Cars', 'Pealp', 'for', 'potatoes', 'racs', 'apple', 'four','scar', 'creams', 'scream']
print "Word list is "; print words; print "\n"
print "Anagrams are "; print combine_anagrams(words); print "\n\n"
