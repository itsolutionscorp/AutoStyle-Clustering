def anagramize(string)
  string.downcase.split(//).sort.join.squeeze
end

def combine_anagrams(words)
  groups = Array.new
  words.each do |word|
    flag = false
	flag1 = false
    groups.each do |group|
      if (anagramize(word) == anagramize(group[0]))
	    group.each do |ele|
		  if (word == ele)
		    p "#{word} in #{group}"
            flag = true
			break
		  end
		end
        if !flag
		  group << word
		  flag1 = true
		  break
		end
      end
    end
    if !flag1 and !flag
      groups << [word]
    end
  end
  groups
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#words = ['A','A']
# input:
print combine_anagrams(words)
print "\n"
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]

