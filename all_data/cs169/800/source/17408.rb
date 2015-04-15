# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  words.sort!{|a, b| a.length <=> b.length}
  letters = words.map{|w| w.downcase.split(//).sort}
  outp = Array.new 
  prev_a = []
  for w in words
    if letters[words.index(w)] == prev_a
      outp[outp.length - 1].push(w)
    else
      outp.push([w])
      prev_a = letters[words.index(w)]
    end
  end
  return outp
end

#p combine_anagrams(['caRs', 'for', 'potatoes', 'rAcs', 'four','scar', 'creams', 'scream'])
