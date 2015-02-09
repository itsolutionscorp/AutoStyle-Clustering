def combine_anagrams(words)
  hash = Hash.new
  words.each do |str|
    sort = str.chars.sort.join
    hash[sort] = Array.new if (hash[sort] == nil)
    ar = hash[sort]
    ar[ar.length] = str
    hash[sort] = ar
    ar = nil
  end
  hash.values
end