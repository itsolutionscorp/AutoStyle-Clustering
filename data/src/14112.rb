def combine_anagrams(words)
  anagram = Array.new
  while words.length > 0 do
    group = Array.new
    first = words[0]
    words.each do |word|
    if(first.downcase.chars.sort.join == word.downcase.chars.sort.join)
        group << word
        end
    end
    anagram << group
    words.reject! {|word| first.downcase.chars.sort.join == word.downcase.chars.sort.join}
  end
  return anagram
end

#p combine_anagrams ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'cReams', 'scream']