
#for each of the word:
#use sorted lowercased word as hash key
#if key exist, add word to value array
#if not exist, create array and add word to array
def combine_anagrams(words)
  groupHash = Hash.new
  words.each do |w|
    sortedLcase = w.downcase.chars.sort.join
    if (groupHash.has_key?(sortedLcase))
        puts "adding #{w} to anagram group key=#{sortedLcase}"
        groupHash[sortedLcase].push(w)
    else
        puts "creating anagram group with key=#{sortedLcase}"
        groupHash[sortedLcase] = [w];
    end
  end
  
  return groupHash.values
end
