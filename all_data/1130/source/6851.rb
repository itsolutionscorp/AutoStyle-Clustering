class String
	def sort
		self.chars.sort.join
	end
end

def combine_anagrams(words)
	anagram_hash = {}
	anagram_hash.default=[]
	
	words.each {|word| anagram_hash[word.downcase.sort]+=[word]}
	
	anagrams = []
	anagram_hash.each_value { |list| anagrams+=[list] }
	anagrams
end
