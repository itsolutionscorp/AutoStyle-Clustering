def combine_anagrams(words)
    anagrams = {}
    words.each do |word|
        key1 = word.downcase.scan(/./).sort
        if (anagrams.has_key?(key1)) then anagrams[key1].concat([word]) else anagrams[key1]=[word] end
    end
    anagrams.values
    
end

if __FILE__ == $0
  print  combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])
end