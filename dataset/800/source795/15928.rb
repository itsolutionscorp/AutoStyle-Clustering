#############################
# Homework1:Part 3
#

def combine_anagrams(words)
    combined = Array.new();
    anagrams = Hash.new();
    
    words.sort.each do |w|
        a = w.downcase.chars.sort.join   #sort(&:casecmp) OR sort{ |a,b| a.casecmp(b)}
        
        if !anagrams[a] 
            anagrams[a] = [w]
        else
            anagrams[a] += [w]
        end
    end

    anagrams.each do |a,b|
        combined += [b.sort]
    end
    
    return combined
end

class Test
    def self.test_combine_anagrams()
        #CASES[]:
        #['cars','for','potatoes','racs','four','scar','creams','scream'],[["cars","racs","scar"],["four"],["for"],["potatoes"],["creams","scream"]]
        
        words = ['cars','for','potatoes','racs','four','scar','creams','scream']
        expected = [["cars","racs","scar"],["creams","scream"],["for"],["four"],["potatoes"]]
        
        got = combine_anagrams(words)
        
        if ( got != expected)
            puts("FAILED:#{got} should be #{expected}")
            return false
        else
            return true          
        end
    end
end
