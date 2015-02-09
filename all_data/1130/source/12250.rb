# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
    word_list = Hash.new{|h, k| h[k] = []}
    
    words.each do |word|
        sorted = word.downcase.chars.sort.join
        #        if not (word_list.include? sorted and word_list[sorted].include?(word))
           word_list[sorted] << word
        # end
   end
    
    word_list.map { |key,value| value }
end


if __FILE__ == $0
    puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'Scream', 'scream']).inspect

end