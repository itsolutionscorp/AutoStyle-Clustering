def combine_anagrams(words)
        def anagrams?(string1, string2)
                return string1.downcase.split(//).sort == string2.downcase.split(//).sort
        end
        
        groups = []
        
        until words.length == 0
                word = words.pop
                added_flag = false
                groups.each do |group|
                        if anagrams?(group[0], word)
                                group << word
                                added_flag = true
                        end
                end
                if !added_flag
                        groups << [word]
                end
        end
        
        return groups
end
 



puts combine_anagrams(['cars', 'for', 'rof','potatoes', 'racs', 'four','scar', 'creams','scream']).to_s

