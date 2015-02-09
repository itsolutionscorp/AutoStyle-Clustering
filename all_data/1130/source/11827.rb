# Part 3
def combine_anagrams(words)
    groups = Hash.new(0)
    words.each do |word|
        if groups.has_key?(word.downcase.split('').sort)
            groups[word.downcase.split('').sort].push(word)
        else
            groups[word.downcase.split('').sort] = [word]
        end
    end
    return groups.values 
end