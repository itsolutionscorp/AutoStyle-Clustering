def combine_anagrams(words)
    h = Hash.new([])
    words.each { |w| h[w.downcase.split(//).sort.join] += [w] }
    c = []
    h.keys.each { |k| c << h[k] }
    return c
end


    
    
    
    
    
    
