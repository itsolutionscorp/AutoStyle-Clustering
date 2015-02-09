def combine_anagrams(words)
    h = Hash.new
    words.each do |word|
        key = word.downcase.split( '' ).sort.join
        (h[key] ||= []) << word
    end
    h.values
end

