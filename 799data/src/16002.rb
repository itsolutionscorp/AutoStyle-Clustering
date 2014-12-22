def combine_anagrams(words)
    grp = {}
    words.each { |ww|
        norm = ww.downcase().split('').sort().join()
        if grp[norm]
            grp[norm] << ww
        else
            grp[norm] = [ww]
        end
    }
    grp.values
end
