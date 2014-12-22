class String
  def sort
	self.downcase.chars.sort.join
  end
end


def combine_anagrams(words)
  anagrams = []
  
  words.each do |word|
	anagrams << words.select { |w| w.sort == word.sort }
  end
  
  anagrams.uniq!
end