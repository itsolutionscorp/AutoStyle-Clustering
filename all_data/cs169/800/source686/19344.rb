# input:['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
if words.length >0
  newword=words[0].downcase.chars.sort_by(&:downcase).join 
else
 return []
end
a=[["dummy"]]
a[0][0]=newword 
words.each {|word|
  b=0
  newword=word.downcase.chars.sort_by(&:downcase).join 
  for w in 0..a.length-1 do 
    if a[w][0]==newword 
      a[w].insert(a[w].length,word)
      b=1
    end
   end
  if b==0
     newarray=Array.new
    newarray[0]=newword
    newarray[1]=word
    a.insert(a.length,newarray)
  end
  }
#delete first item of array used for searching
a.each {
|c|
c.delete_at(0)
}
puts(a)
return a

end

test=['cars','CarS', 'CARS','for','fOr', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams(test)
test=['c','C', 'd','D']
combine_anagrams(test)
test=[]
combine_anagrams(test)
test=['c','C', 'd','D']
combine_anagrams(test)




