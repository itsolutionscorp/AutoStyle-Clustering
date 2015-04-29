# p3.rb

def combine_anagrams(words)
  anagramHash = Hash.new
  words.each do |word|
    wd = word.downcase.chars.sort.join
    #anagramHash[wd].concat([word]);
    if(anagramHash[wd] == nil)
      anagramHash[wd] = [word];
    else
      anagramHash[wd].concat([word]);
    end
  end
  result = Array.new
  anagramHash.each do |key,val|
    #puts "#{key}:#{val}"
    result = result.concat([val])
  end
  return result
end

puts combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s