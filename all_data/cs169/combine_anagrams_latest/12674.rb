
# PART3 


def combine_anagrams(words)
    finish = Hash.new()
    words.each do |str|
        sorted = str.split('').sort.join
        if finish.has_key?(sorted)
            finish.store(sorted, finish.fetch(sorted).push(str))
        else
            finish.store(sorted, Array.new(1,str))
        end
    end
    return finish.values
end

#wrds = ['cars','for','potatoes','racs','four','scar','creams','scream']