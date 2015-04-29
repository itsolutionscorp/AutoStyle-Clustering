#---part.3---
def combine_anagrams(words)
  p words
  arr_words=Array []
  words.each do |i|
    flag=false
    for j in 0..arr_words.length-1
      if (!flag)
        if (arr_words[j][0].chars.sort.join==i.chars.sort.join)
          arr_words[j].push(i)
          flag=true
       end
      end
      break if (flag)
    end
    if (!flag)
     arr_words.push(Array[i])
    end
  end
  p (arr_words);
end
words=Array['cars','for','potatoes','racs','four','scar','creams','scream']
combine_anagrams(words)

