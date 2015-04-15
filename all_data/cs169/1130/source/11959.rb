def combine_anagrams(words)
#
i=0
  
  words2= Array.new
  words3= Array.new
  words4= Array.new
  
  words.each do |w|
    words2[i]=w.downcase.chars.sort.join
    i=i+1
  end

  words2.each do |w|
      words4.push words2.index w 
  end
  
  words4=words4.uniq


i=0
  words2.each do |w|

    if ((words2.index w) == i)

      tmp=Array.new
      tmp.push words[i]
      words3.push tmp
    else

      tmp=words3[words4.index (words2.index w)]
      tmp.push words[i]
      words3[words4.index (words2.index w)]=tmp     
    end  
      #print tmp
      
    i=i+1 
  end



  return words3

end
