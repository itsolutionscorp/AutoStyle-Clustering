
# Given an array of strings returns groups of anagrams 
def combine_anagrams(words)
	groups = {}
	words.each do |w|
	  norm = w.downcase.chars.sort.join
	  groups[norm] ||= []
	  groups[norm] << w
	end
	return groups.values
end
