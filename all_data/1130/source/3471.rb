def sort word
  word.downcase.chars.sort.join
end

def combine_anagrams(words)
  words.sort_by{|word| sort word}.chunk{|word| sort word}.to_a.map{|e|e[1]}
end
