def combine_anagrams(words)
    h = {}
    words.each {|w| h.fetch(w.downcase.split(//).sort.join){|i| h[i] = []} << w}
    h.values
end
