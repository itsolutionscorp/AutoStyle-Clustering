def combine_anagrams(words)
	sorted = []
	outer = []
	inner = []
		
	words.each { |word| sorted << word.downcase.chars.sort { |a, b| a.casecmp(b) } .join}
	
	outerCount = 0
	innerCount = 0
	hash = {}
	for matchOuter in sorted
		if hash.has_key?(sorted.index(matchOuter)) == false
			for matchInner in sorted
				if matchOuter.casecmp(matchInner) == 0 then
					inner << words[innerCount]
				end
				innerCount += 1
			end
			hash[sorted.index(matchOuter)] = 'true'
			outer << inner.uniq
			inner = []
			innerCount = 0
		end
		outerCount += 1
	end
	return outer
end

##
def combine_anagrams_old(words)
	sorted = []
	outer = []
	inner = []
		
	words.each { |word| sorted << word.downcase.chars.sort { |a, b| a.casecmp(b) } .join}
	outerCount = 0
	innerCount = 0
	hash = {}
	for matchOuter in sorted
		if hash.has_key?(sorted.index(matchOuter)) == false
			for matchInner in sorted
				if matchOuter.casecmp(matchInner) == 0 then
					inner << words[innerCount]
				end
				innerCount += 1
			end
			hash[sorted.index(matchOuter)] = 'true'
			outer << inner
			inner = []
			innerCount = 0
		end
		outerCount += 1
	end
	return outer
end