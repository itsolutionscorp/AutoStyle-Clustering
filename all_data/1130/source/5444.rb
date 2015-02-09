def combine_anagrams(words)

ana_table = Hash.new{|h, k| h[k] =[]}
words.each {|x| ana_table[x.strip.downcase.split(//).sort] << x}

ana_list = Array.new{|h| h=[]}
ana_table.each {|x,y| ana_list << y}

return ana_list

end

