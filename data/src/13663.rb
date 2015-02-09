def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    hash.key?(key) ? ((hash[key] << word)) : (hash[key] = [word])
  end
  array = Array.new
  hash.each { |k, v| (array << v) }
  return array
end