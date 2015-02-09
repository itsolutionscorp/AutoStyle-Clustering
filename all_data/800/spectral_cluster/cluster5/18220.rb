

def combine_anagrams(words)
    # creating hash for string the anagrams
    h = Hash.new{|h, k| h[k] = []}
    words.each do |word|
        h[word.downcase.chars.sort.join] << word
    end
    return h.values
end

