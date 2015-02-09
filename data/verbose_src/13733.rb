def combine_anagrams(words)
  
  match               = Array.new(0);
  anagram             = Array.new(0);
  ordered_array       = Array.new(0);
  copy_ordered_array  = Array.new(0);
  
  for i in words
    word_array  = Array.new(0);
    word_array  =i.split(%r{\s*});
    
    count =0
    for r in word_array
      word_array[count] = r.downcase
      count +=1
    end
    
    ordered_array[ordered_array.length]= Array.new(0);
    ordered_array[ordered_array.length-1][0]=word_array.sort.join;
    ordered_array[ordered_array.length-1][1]=i;
  end
  
  for i in ordered_array
    copy_ordered_array[copy_ordered_array.length] = i  
  end
  
  for i in ordered_array
    for j in copy_ordered_array
      if(i[0]==j[0])
        match[match.length]=j[1];
      end
    end
    if(match.length>0)
      anagram[anagram.length]=match
      for h in match
        for q in copy_ordered_array
          if(h == q[1])
            copy_ordered_array.delete(q);
          end
        end
      end
      match = Array.new(0);
    end
  end
  
 return anagram.sort
end
