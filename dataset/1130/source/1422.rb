def combine_anagrams(words)
# <YOUR CODE HERE>
h = Hash.new
words.each{|x| h[x.downcase.split(//).sort.join] = []}
words.each{|x| h[x.downcase.split(//).sort.join] << x}
a = []
h.each{|key, value| a << value}
return a
end