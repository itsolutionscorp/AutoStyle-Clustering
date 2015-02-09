# anagrams - combine_anagrams

def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    anagrams[key] ||= []
    anagrams[key] << word
  end
  return anagrams.values
end