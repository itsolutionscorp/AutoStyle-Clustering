
def combine_anagrams(words)
  groups = {}
  
  words.each { |word|
    letters = word.downcase.split("").sort.join('')

	if ! groups[letters]
	  groups[letters] = []
	end
	
	groups[letters].push( word )
  }
  
  return groups.values()
end