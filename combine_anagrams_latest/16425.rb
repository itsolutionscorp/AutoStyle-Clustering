def combine_anagrams(words)
  hashmap = {};
  result = []; 

  words.each { |word| 
     puts word;
     key = word.downcase.each_char.sort.join;
     if (hashmap[key] == nil)
       hashmap[key] = result.length;
       result[result.length] = [word];
     else
       result[hashmap[key]] += [word];
     end;
  };
  return result;
end

=begin
#tests
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);
=end
