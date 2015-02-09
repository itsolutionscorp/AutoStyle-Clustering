class String
    def chars
        self.split("").each { |char|
            yield char
        }
    end
end

# input: 
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  # sory all the letters in the words

    anagrams = Hash.new

    words.each { |word|
        sortedWord = Array.new
        word.chars { |char|
            sortedWord << char
        }

        key = sortedWord.sort.join
        key.lower!

        if (!anagrams[key])
            anagrams[key] = Array.new
        end

        anagrams[key] << word
    }

    returnArray = Array.new

    anagrams.each { |x, y|
        returnArray << y
    }

    return returnArray

end


