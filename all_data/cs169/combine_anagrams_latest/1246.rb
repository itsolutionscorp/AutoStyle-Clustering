def combine_anagrams(words)

    #empty container for result
    hash = {}
    result = [] 
    
    words.each do |word|
        #ignore case
        temp = word.downcase
        temp = temp.chars.sort.join

        #insert into hash, create the has if not exist
        if hash[temp] == nil 
            hash[temp] = []
        end

        hash[temp] << word
        
    end

    #move all the hash value to list
    hash.each_value { |value| result << value }
    
    return result
  
end

