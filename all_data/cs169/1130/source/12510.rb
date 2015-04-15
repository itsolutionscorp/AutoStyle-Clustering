def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |str|
     sorted = str.downcase.chars.sort.join	# join all characters in the string
     if (anagrams[sorted]== nil)	# if unique
       anagrams[sorted] = Array.new # create sorted anagram list inside array
     end
     arr = anagrams[sorted]			# duplicate anagram list
     arr[arr.length] = str			# set array's last index to string
     anagrams[sorted] = arr			# 
     arr = nil
  end
  
  anagrams.values
end