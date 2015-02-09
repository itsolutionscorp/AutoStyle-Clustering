
def createArray word
    wordComp = word.downcase
    array = []
    wordComp.chars {|c| array << c.ord}
    array.sort!
    array
end

def compareAnagram word1, word2
    array1 = createArray word1
    array2 = createArray word2
    array1 == array2
end

def findAnagram anagrams, word
    found = false
    (0...anagrams.size).each do |idx|
        if compareAnagram( anagrams[idx][0], word) == true
            return [true, idx]
        end
    end
    return [false, -1]
end

def combine_anagrams(words)
    res = []
    words.each do |word|
        found, idx = findAnagram( res, word ) 
        if found == false
            res << [word]
        else
            res[idx] << word
        end
    end
    res
end

