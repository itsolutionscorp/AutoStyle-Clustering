def combine_anagrams(words)
  hash = Hash.new
  words.each do |str|
    sorted = str.downcase.chars.sort.join
    hash[sorted] = Array.new if (hash[sorted] == nil)
    arr = hash[sorted]
    arr[arr.length] = str
    hash[sorted] = arr
    arr = nil
  end
  hash.values
end