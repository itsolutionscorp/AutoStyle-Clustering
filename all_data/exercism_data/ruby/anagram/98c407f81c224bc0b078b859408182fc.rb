class Anagram
@@word
def initialize(word)
  @@word = word
end

def match(candidates)
  anagrams = []
  candidates.each do |candidate|
  	anagrams << candidate if candidate.downcase.chars.sort == @@word.downcase.chars.sort and candidate.downcase != @@word.downcase 
  end
  anagrams
end
end
