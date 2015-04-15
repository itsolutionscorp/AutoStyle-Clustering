
def combine_anagrams(words)
    hash = Hash.new{|h,k| h[k] = []}
    words.each { |word| hash[word.each_char.sort.join] << word }
    return hash.values
end

