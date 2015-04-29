def combine_anagrams(words)
    the_hash = Hash.new()
    words.each { |x| 
        if(the_hash[x.downcase.split(//).sort] == nil) 
            a = Array.new()
            a[0] = x
            the_hash[x.downcase.split(//).sort] = a
        else 
            the_hash[x.downcase.split(//).sort].push(x)
        end }
    the_hash
    result = Array.new()
    the_hash.each_value {|value| result.push(value) }
    result
end

