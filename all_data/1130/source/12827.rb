def combine_anagrams(words)
   map = Hash.new
   words.each do |word|
      key = word.downcase.chars.sort.to_s
      map[key] ||= Array.new
      map[key] << word
   end
   return map.values
end

puts combine_anagrams(["cars","scar","boat"])
