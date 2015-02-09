def combine_anagrams(wds)
        anagrams = {}
        wds.each{ |w|
                grp = w.downcase.split(//).sort.join
                if anagrams.has_key?(grp) then
                        anagrams[grp].insert(-1, w)
                else
                        anagrams[grp] = [w]
                end
        }
        anagrams.values
end

#input1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#rez1 = combine_anagrams input1
#puts rez1.to_s
