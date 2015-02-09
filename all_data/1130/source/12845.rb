def combine_anagrams(words)
    h = {}
    words.each { |w| h[w.downcase().split(//).sort().join()] = [w] + (h[w.downcase().split(//).sort().join()] == nil ? [] : h[w.downcase().split(//).sort().join()]) }
    return h.values()
end

