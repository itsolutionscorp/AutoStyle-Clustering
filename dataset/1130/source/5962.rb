def combine_anagrams(words)
    h = Hash.new{|hash, key| hash[key]=key.downcase.split('').sort.to_s}
    words.each{|x| h[x]}
    category = h.invert
    category.each_key{|key| category[key]=[]}
    words.each{|x| category[h[x]]<<x}
    return category.values
end
