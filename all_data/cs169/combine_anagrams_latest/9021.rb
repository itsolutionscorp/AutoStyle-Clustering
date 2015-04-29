# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
    # sort all words alphabetically and create a hash with the key is the word, value is its sorted one
    sorted_words = words.collect { |x| [x, x.downcase.chars.sort.join]}.sort_by{
        |x, sorted_x| sorted_x
    }
    result = [];
    prev_word = "";
    # loop through all word
    sorted_words.each do |key, value|
        if value != prev_word # create another array in result
            result = result.concat([[key]]);
            prev_word = value
        else # append to last array of result
            result.last.concat([key]);
        end
    end
    return result

end