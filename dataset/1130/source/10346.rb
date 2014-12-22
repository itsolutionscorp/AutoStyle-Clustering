def combine_anagrams(words)
    @tmp=[]
    @arr=[]
    @ash={}
    words.each { |word|
        @tmp=word.downcase.split("").sort
        ordd=@tmp.join("")
        prev=@ash.fetch(ordd, '')
        if (prev=='') then
            @ash[ordd]=[word]
        else
            @ash[ordd]=prev<<word
        end
    }
    @ash.each { |key, val|
        @arr<<val
    }
    @arr
end
