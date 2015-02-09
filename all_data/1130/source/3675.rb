def is_anagram(word1,word2)
  return word1.downcase().split(//).sort()==word2.downcase().split(//).sort()
end

def combine_anagrams(words)
  dim = words.length()
  group=0
  for i in 0..dim-1
    anagr=false
    for j in 0..i-1
      if(is_anagram(words[i],words[j]))
        anagr=true
      end
    end
    if(anagr==false)
      group+=1
    end
  end
  h = Array.new(group) { Array.new() }
  index=0
  words.each { |w|
    for i in 0..index
      if ( h[i][0] == nil )
        h[index][0]=w
        index+=1
        break
      else
        if is_anagram(w,h[i][0])
          h[i][h[i].length()]=w
          break
        end
      end
    end
  }
  return h
end

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

puts(1)
puts( is_anagram("a","a") )

puts(2)
puts( is_anagram("a","A") )

puts(3)
puts( is_anagram("a","b") == false )

puts(4)
puts( is_anagram("ab","ba") )

puts(5)
puts( combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'] ) == [["cars", "racs", "scar"], ["for"], ["potatoes"],["four"], ["creams", "scream"]] )
#puts([["cars", "racs", "scar"], ["for"], ["potatoes"],["four"], ["creams", "scream"]])

puts(6)
puts( combine_anagrams(['cars', 'for', 'potatoes', 'racq', 'four','wcar', 'ereams','scream'] ) == ['cars', 'for', 'potatoes', 'racq', 'four','wcar', 'ereams','scream'] )
#puts(['cars', 'for', 'potatoes', 'racq', 'four','wcar', 'ereams','scream'])
