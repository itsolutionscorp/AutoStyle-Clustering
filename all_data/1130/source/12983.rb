


def combine_anagrams(words)
  h = {}
  words.each do |single|
    downcased=single.downcase();
    splited=downcased.split(//);
    sorted = splited.sort()
    joined = sorted.join();
    if(h.has_key?(joined))
      h[joined] += [single];
    else
      h[joined] = [single];
    end
  end
  r = h.flatten;
  #print r;
  #print "\n";
  array2 = [];
  r.each_with_index do |item,index|
    if (index %2 == 1) then
      array2.push(item)
    end
  end
  #print array2;
  #print "\n";
  return array2;
end


#print combine_anagrams( ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#print "\n"
