def combine_anagrams(words) 
    anagrams = {}
    # iterate through array words as word
    words.each do | word |
        # sort the current word and store as sorted_word
        sorted_word = word.downcase.chars.sort.join
        # if sorted_word is already a hash key, then add word as another anagram
        if anagrams.has_key?(sorted_word) then
            anagrams[sorted_word] << word
        else 
            # sorted_word is not already a key so add the key with word as the first element of the value array
            anagrams[sorted_word] = [word]
        end  # if
    end #end of loop through array words 

    results = []
    # iterate through the hash and return the value array as anagram. append to results.
    anagrams.each do |k, anagram |
        results << anagram
    end
    return results
end