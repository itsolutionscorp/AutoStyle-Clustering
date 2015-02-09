
def combine_anagrams(words)

# sort array elements by chars
  words_sortedchars = Array.new;
  words.each do |word|
    words_sortedchars.push(word.downcase.chars.sort(&:casecmp).join)
  end

# create hashmap of anagram indexes
  words_idx_count = Hash.new(0);
  words_sortedchars.each_with_index do |item, index|  
    if (words_idx_count.has_key?(item))
      words_idx_count[item] = words_idx_count[item].to_s + ',' + index.to_s 
    else
      words_idx_count[item] = index.to_s;
    end
  end

#get original anagram words based on their indexes
  anagram_arr = Array.new;
  words_idx_count.each_pair do |k,v| 
    anagram_arr.push(v.split(',').collect {|i| words[i.to_i] })  
  end

  return anagram_arr;

end

#Test
# input:
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'];
#words = ['cars', 'for', 'Cars','potatoes', 'racs', 'bB', 'four', 'bb', 'scar', 'a', 'creams', 'A','scream'];
#p combine_anagrams(words)

#Expected output:
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
