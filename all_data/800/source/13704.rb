#require 'set'

def combine_anagrams(words)
ans={}
#words = words.to_set
words.each do |word|
a = word.downcase.chars.sort.join
if ans[a].nil? 
ans[a] = [] 
end
ans[a] << word
end
vals = []
ans.each_value do |val|
vals << val
end
vals
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'A', 'a', 'Cars', 'pOtateos']

a= combine_anagrams(words)
puts a
puts 
puts a.size






