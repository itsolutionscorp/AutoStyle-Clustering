#helper method
def is_anagram(word1, word2)
array1 = word1.downcase.split(//)
array1.sort!()
array2 = word2.downcase.split(//)
array2.sort!()
return (array1 == array2)
end
#main method
def combine_anagrams(words)
found = false
groups = Array.new
words.each{|word|
	if (groups.empty?)
		groups.push(Array.new(1, word))
	else
		found = false
		groups.each{|group|
		if (is_anagram(group.at(0), word))
			group.push(word)
			found = true
			break
		end
		}
		if (found == false)
			groups.push(Array.new(1, word))
		end
	end
}
return groups
end