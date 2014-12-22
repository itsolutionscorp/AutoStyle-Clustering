def combine_anagrams(words)
# <YOUR CODE HERE>
gr=Array.[]
while words.size>0
el=Array.[]
words.each {|x| el<<x if x.downcase.scan(/\w/).sort == words.first.downcase.scan(/\w/).sort}
words.delete_if {|x| (el&[x]).size>0}
gr<<el
end
return gr
end