def combine_anagrams(words)
  anagram_groups = []
  checked = []

  words.each do |word|
    canonical = canonical_repr(word)
    if not checked.include?(canonical)
      anagram_groups << find_anagrams(canonical, words)
      checked << canonical
    end
  end
  anagram_groups
end

def find_anagrams(canonical, words)
  found = []
  words.each { |word| found << word if canonical == canonical_repr(word) }
  found
end

def canonical_repr(word)
  word.downcase.gsub(/[^\w]/,"").split("").sort
end
