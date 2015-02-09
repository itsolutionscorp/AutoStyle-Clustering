
def anakey(word)
    return word.chars.sort.join
end

def combine_anagrams(words)
    ht = Hash.new(Array.new)
    ans = Array.new
    for w in words
        if ht.has_key? anakey(w.downcase)
            ht[anakey(w.downcase)] = ht[anakey(w.downcase)] << w
        else
            ht[anakey(w.downcase)] = [w]
        end
    end
    for k in ht.keys
        ans = ans << ht[k]
    end
    return ans
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
