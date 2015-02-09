def combine_anagrams(words)
    gram=Array.new
    words.each do |word|
        gram << [word.downcase.chars.sort.join,gram.length]
    end
    gram.sort!
    out=Array.new
    temp=Array.new
    for i in 1..gram.length
        temp.push(words[gram[i-1][1]])
        if i==gram.length then out.push(temp.compact)
        break
        elsif gram[i-1][0]==gram[i][0] then next
        else out.push(temp.compact)
        temp.clear
        end
    end
    return out
end