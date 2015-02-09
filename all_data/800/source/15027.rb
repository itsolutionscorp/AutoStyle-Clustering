def anagram_key(string)
  key =""
  string.downcase.chars.sort.each { |c| key += c }
  key
end

def combine_anagrams(words)
  raise ArgumentError unless words.kind_of?(Array)
  keys = words.collect{|w| anagram_key(w)}.uniq
  anagrams = keys.collect {|k| words.select {|w| anagram_key(w)==k } }
end