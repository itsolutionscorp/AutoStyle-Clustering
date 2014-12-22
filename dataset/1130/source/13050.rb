 
def combine_anagrams(words)
    a=Hash.new()
    words.each { |word|
        k = word.downcase.chars.sort.join
        if a.has_key?(k) then
          a[k].push word
        else
          a[k]=[word]
        end
    }
    return(a.values)
  end

