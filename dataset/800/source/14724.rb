
def combine_anagrams( words )
    
    tmp = {};
    
    words.each { |word|
        
        key = word.downcase.chars.sort.join;
        tmp[key] = tmp[key] || [];
        tmp[key] << word;
        
    };
    
    return tmp.values;
    
end;
