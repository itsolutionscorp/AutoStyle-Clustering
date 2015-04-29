def combine_anagrams(words)
  dico = Hash.new([])
  words.each {|x|
    x1 = x.split(//).sort {|a,b| a.downcase <=> b.downcase}.join
    dico[x1.downcase] += [x]}
  arr = []
  dico.each_value {|x| arr += [x]}
  arr
end

#puts "algodon".split(//).sort
#print combine_anagrams(['cars', 'CARS','for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

