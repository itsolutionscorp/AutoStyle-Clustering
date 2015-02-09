#part 3
def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    group = words.find_all { |item| item.downcase.chars.sort.join == word.downcase.chars.sort.join }
    anagrams.push group
  end
  anagrams.uniq
end