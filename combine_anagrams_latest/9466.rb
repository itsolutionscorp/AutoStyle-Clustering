def combine_anagrams(words)
    temp_words = Array.new(words)
    temp_words.each_index do |i|
        temp_words[i] = temp_words[i].downcase.chars.sort.join
    end
    h = Hash.new
    temp_words.each_index do |i|
        if h.has_key?(temp_words[i])
            h[temp_words[i]] << i
        else
            h[temp_words[i]] = Array.new
            h[temp_words[i]] << i
        end
    end
    groups = Array.new
    h.each_value do |val|
        a = Array.new
        val.each do |i|
            a << words[i]
        end
        groups << a
    end
    return groups
end

def test_combine_anagrams()
    puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
end

#Test
test_combine_anagrams()