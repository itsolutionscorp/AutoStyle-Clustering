def combine_anagrams(words)
    start = words
    ans = Array.new(0)
    freq = Hash.new(0)
    numlen = 0
    words.each do |w| 
        s = w.downcase.unpack("c*").sort.pack("c*")
        if freq.has_key?(s) == false
            freq[s] = numlen
            numlen += 1
        end
        curnum = freq[s]
        if ans[curnum]==nil
            ans << Array.new(0)
        end
        ans[curnum] << w
    end
        return ans
end