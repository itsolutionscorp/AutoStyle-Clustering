require 'pp'
#  Part 3: anagrams
#  An anagram is a word obtained by rearranging the letters of another word.  For 
#  example, "rats", "tars" and "star" are an anagram group because they are made up of the same 
#  letters.
#  Given an array of strings, write a method that groups them into anagram groups and returns 
#  the array of groups.  Case doesn't matter in classifying string as anagrams (but case should be 
#  preserved in the output), and the order of the anagrams in the groups doesn't matter.
#  Example:
#  # input: 
#  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#  ["creams", "scream"]]
#  # HINT: you can quickly tell if two words are anagrams by sorting their
#  #  letters, keeping in mind that upper vs lowercase doesn't matter
class String
    def anagram?(word)
        self.isort == word.isort
    end
    def isort
        self.downcase.split('').sort.join('')
    end
end
def combine_anagrams(words)
    groups = []
    words.each do | word | 
        group = words.collect { |other| word.anagram?(other) ? other : nil }
        groups << group.compact
    end
    groups.uniq
end

tests = [
            %w{ cars scars }, 
            %w{ ars scars }, 
            %w{ cars cars }, 
            %w{ racs cars }, 
            %w{ mars cars },
            %w{ cReams SCREAM }, 
            %w{ a A }, 
            %w{ cars CARS CaRS}, 
            %w{cars a A B B Cars for potatoes racs fourscar creams scream}, 
]
#tests = [ %w{ cars cars } , 
            #%w{ a A }
        #]
tests.each do | words | 
    puts "Running: "
    pp words
    pp combine_anagrams words
end


