def combine_anagrams(words)
  anagrams=[]
  words.each do |w|
    found=false
    anagrams.each do |a|
      if w.downcase.chars.sort.join==a[0].downcase.chars.sort.join
        a<<w
        found=true
        break
      end
    end
    if !found
        anagrams<<[w]
    end
  end
  anagrams
end

#words=['cars','for','hello','potatoes','racs','hEllO','four','scar','creams','scream']
#puts combine_anagrams(words)

