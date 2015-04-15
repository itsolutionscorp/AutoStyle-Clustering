def combine_anagrams(words)
    anagrams = Hash.new { |h,k| h[k] = [] }
    res = []
    words.map { |x|
        anagrams[x.downcase.chars.sort.join].push(x)
    }
    anagrams.each_pair { |k,v|
        res << v
    }

    return res
end
