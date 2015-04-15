def combine_anagrams(words)
  hash = Hash.new(nil)
  result = []
  words.each do |word|
    sorted = word.downcase.chars.sort.to_s
    if( hash[sorted] == nil)
       hash[sorted] = [word]
    else
       hash[sorted].push(word)
    end
  end
  
  hash.keys.each do |key|
    result << hash[key]
  end
  
  result
end
