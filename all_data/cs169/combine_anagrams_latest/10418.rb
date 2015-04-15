def combine_anagrams(words)
    groups = words.group_by {|x| anagram(x) }
    array = []    
    groups.each {|k,v| array.push(v)}
    array
end


def anagram(x)    
    x.downcase.split(//).sort.join
end

#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#puts combine_anagrams(input).length
