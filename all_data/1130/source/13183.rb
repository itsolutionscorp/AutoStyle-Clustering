def combine_anagrams(words)
        hs = {}
        words.each{ |j|
                pr = j.downcase.split(//).sort.join
                if hs.has_key?(pr) then
                        hs[pr].insert(-1, j)
                else
                        hs[pr] = [j]
                end
        }
        hs.values
end
