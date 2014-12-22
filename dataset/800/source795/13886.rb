
def combine_anagrams(words)
  mem = Hash.new{|h, k| h[k] = []}
  words.map { |word| [word.downcase.chars.sort.join, word] }
       .each_with_object(mem) { |(sign, word), h| h[sign] << word }
end

