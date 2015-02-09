def combine_anagrams(words)
        [] if words.empty?
        combine = Hash.new
        words.each do |word|
                sorted = word.downcase.chars.sort.join
                if combine.has_key?(sorted)
                        combine[sorted] << word
                else
                        combine[sorted] = [word]
                end    
        end
        combine.values
end
 
def test
        words1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
        words2 = []
        words3 = ['A', 'man', 'a', 'plan', 'a', 'Panama']
        puts combine_anagrams(words1) == [["cars", "racs", "scar"], ["for"], ["potatoes"], ["four"], ["creams", "scream"]]
        puts combine_anagrams(words2) == []
        puts combine_anagrams(words3) == [["A", "a", "a"], ["man"], ["plan"], ["Panama"]]
end