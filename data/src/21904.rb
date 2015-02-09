def combine_anagrams(words)
  h = Hash.new() {[]}
  words.each do |word|
#    word = word.downcase
    key = word.downcase.chars.sort.join
    h[key] = h[key] << word # unless h[key].include?(word)
  end
  return h.values
end
