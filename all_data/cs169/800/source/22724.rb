# input: 
words=['cars', 'for', 'potatoes', 'raCs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],  ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

  hash  ={}
  words.each do |word|
    #hash[word]=(word.downcase.sort)
    letters= word.downcase.split(//).sort.join

    #puts "ordered #{word} = #{letters}"
    #puts hash[letters]
    hash[letters]="#{word},#{hash[letters]}"
    #puts "#{letters} contains #{hash[letters]}"
  end
  puts hash.inspect
  arr =[]
  i=0
  hash.each do |group|
    arr[i]=group[1].split(/,/)
    puts "arr #{i} = #{arr[i].inspect}"
    i=i+1
    puts("#{group[1]} split = #{group[1].split(/,/).inspect}")
    #puts (group.split(/,/))
    #puts (group[1].class.to_s)
    #puts (group[1].inspect)
    puts arr.class.to_s
  end
  arr

end
result=combine_anagrams words
  puts "result =#{result.inspect}"

