def combine_anagrams(words)
  h = {}
  c = words.map { |w|
    h[w.downcase.chars.sort.join] == nil ?
    h[w.downcase.chars.sort.join] = [w] :
    h[w.downcase.chars.sort.join] += [w]
  }
  a = []
  h.each_key do |key|
    a << h[key]
  end

  return a
end

# input:
a =  combine_anagrams (['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts a.to_s
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
