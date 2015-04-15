def combine_anagrams(words)
  hash = Hash.new
  words.each do |w|

    tempWord = []
    w.each_char do |c|
      tempWord << c.downcase
    end

    tempWord = tempWord.sort {|a,b| b <=> a}.join

    if hash.has_key?(tempWord)
    else
      hash[tempWord] = Array.new
    end

    hash[tempWord] = hash[tempWord] << w
  end

#print hash

# todo return a list of the contents of the array

  list = Array.new
  hash.each_key do |key|
    list << hash[key]
  end
  return list
end

print combine_anagrams(['hello', 'This', 'is', 'alex', 'this', 'siht', 'htis'])
print combine_anagrams(['h', 't', 'i', 'a', 't', 's', 'h'])
