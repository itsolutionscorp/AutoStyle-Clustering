# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
    d = {}
    w = words.map { |x| d[x] = x.split(%r//).sort.join }

    r = {}
    d.each_value { |val| r[val]=[] }
    words.map { |e| r[d[e]].push(e) }
    r.values
    
end

print combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
