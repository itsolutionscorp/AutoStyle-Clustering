def combine_anagrams(words)
  anagrams = Hash.new
  words.length.times do |i|
    hash_value = 0
    word = words[i].split("").sort!.join("")
    word.length.times do |j|
      hash_value = (hash_value + word.clone.slice!(j).downcase.ord)
    end
    if anagrams.has_key?(hash_value) then
      (anagrams[hash_value] << words[i])
    else
      anagrams[hash_value] = Array.new
      (anagrams[hash_value] << words[i])
    end
  end
  anagrams.values
end

