def combine_anagrams(words)
    len = words.length
    hm = Hash.new
    words.each do |word|
        sortedword = word.downcase.chars.sort.join
        ar = hm[sortedword]
        if (ar == nil)
           ar = Array.new
        end
        ar << word
        hm[sortedword] = ar
     end
     retarr = Array.new
     hm.each do |key,value|
        retarr << value
     end
     retarr
end

words=["cars","scar", "ARCS", "but", "tUB"]
hm = combine_anagrams(words)
p hm
