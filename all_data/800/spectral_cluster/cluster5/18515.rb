#Q3
def combine_anagrams(words)

    #setup anagram group hash
    hasher = Hash.new { |h,k| h[k] = [] }
    #stick each word into a bucket
    words.each { |w| hasher[w.downcase.chars.sort.join] << w }
    hasher.values
end