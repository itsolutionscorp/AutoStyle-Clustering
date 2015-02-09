def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    (not hash.key?(key)) ? (hash[key] = [word]) : (hash[key].push(word))
  end
  hash.values
end

