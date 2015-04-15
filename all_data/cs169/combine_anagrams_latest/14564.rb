def combine_anagrams(words)
  result = Array.new(0)
  words.each do |w1|
    anagram = []
    words.each do |w2|
      if (w2.downcase.chars.sort == w1.downcase.chars.sort)
        anagram << w2
      end
    end
    result << anagram
  end
  return result.uniq
end

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

output = combine_anagrams(input)

print output,"\n"