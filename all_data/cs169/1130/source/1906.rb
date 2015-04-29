class AnagramCombiner
	def combine_anagrams(words)
		groups = {}
		words.each do |word|
		 group = groups[key word] || []
		 group << word
		 groups[key word] = group
		end
		groups.values
	end

	def key(word)
		word.downcase.bytes.sort
	end
end


def combine_anagrams(words)
	AnagramCombiner.new.combine_anagrams(words)
end
