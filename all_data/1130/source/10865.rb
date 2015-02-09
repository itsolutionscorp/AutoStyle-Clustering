def combine_anagrams(words)
h = {}
words.each do |e|
s = e.downcase.chars.sort.join;
if h[s] == nil
h[s] = []
end
h[s].push(e)

end

fa = []

h.each do|k, v|
fa.push(v)
end
return fa
end