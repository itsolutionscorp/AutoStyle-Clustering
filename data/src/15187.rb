def combine_anagrams(words)
  hash = Hash.new(nil)
  result = []
  words.each do |word|
    sorted = word.downcase.chars.sort.to_s
    (hash[sorted] == nil) ? (hash[sorted] = [word]) : (hash[sorted].push(word))
  end
  hash.keys.each { |key| (result << hash[key]) }
  result
end