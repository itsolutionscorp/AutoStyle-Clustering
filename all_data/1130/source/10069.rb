#arr = ["AAA", "BBBB"]
#brr = arr.map{|w| w.downcase}
#print arr

def combine_anagrams(words)
    matchlist = []
    words.each {
        |word|
        found = false
        matchlist.map {
            |list|
            if list[0].downcase.chars.sort.join == word.downcase.chars.sort.join
                found = true
                list.push(word)
            end
        }
        matchlist.push([word]) unless found
    }
    return matchlist
end

#print combine_anagrams(["sdf", "AsdG", "aae", "SFD"])