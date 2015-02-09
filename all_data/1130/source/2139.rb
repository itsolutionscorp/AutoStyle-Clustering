=begin
Part 3: anagrams
An anagram is a word obtained by rearranging the letters of another word. For
example, "rats", "tars" and "star" are an anagram group because they are made up of the same
letters.
Given an array of strings, write a method that groups them into anagram groups and returns
the array of groups. Case doesn't matter in classifying string as anagrams (but case should be
preserved in the output), and the order of the anagrams in the groups doesn't matter.
Example:
# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
=end

def sorted(s)
    ss = s.downcase.split('').sort.join
end

def combine_anagrams(words)
    outa = []
    words.each do |word1|
    		found = false
    		outa.each do |ou|
    				if ou.include?(word1)
    						found = true
    				end
    		end
    		if !found
	        sw1 = sorted(word1)
					a1 = []
			    words.each do |word2|
							sw2 = sorted(word2)
							if sw1 == sw2
			    	  		a1.push(word2)
							end
			    end
			    outa.push(a1)
    		end
		end
    return outa    
end

def check(in_a, out_a)
    failed = false
    c_a = combine_anagrams(in_a)
    c_a.each do |a|
        if !out_a.include?(a)
            failed = true
            break
        end
    end
    if failed
        print "Expected: "
        print out_a
        print "\n"
        print "Actual:   "
        print c_a
				print "\n"
    else
        print "Passed\n"
    end 
end

in_a = ['a', 'a', 'b', 'b', 'c', 'd']
out_a = [["a", "a"], ["b", "b"], ["c"], ["d"]]
check(in_a, out_a)

in_a = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
out_a = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
check(in_a, out_a)

in_a = ['cars', 'a', 'for', 'potatoes', 'racs', 'A', 'four', 'scar', 'creams', 'scream', 'a', 'scar']
out_a = [['a'], ["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
check(in_a, out_a)

