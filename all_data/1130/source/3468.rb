words= ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their # letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words) 
  temparr=[]
  temp=[]
  num=0
  finarr=[]
  clasarr=[]
  
  words.each do |f|
    temparr<<f.downcase.split("").sort.join
  end
  newarr=Array.new
  for i in (0..(temparr.length)) do
    for j in (0..(temparr.length)) do
      if temparr[i]==temparr[j]
        newarr[i]=Array.new
        newarr[i].push(i,j)
        clasarr[i]=Array.new
        clasarr[i].push(j)
      end
    end
  end
  
  
  clasarr.uniq.each do |k|
    newarr.each do |p|
      if p[1]==k[0]
        if words[p[0]] != nil
          temp.push(words[p[0]])
        end
      end
    end
    if temp != []
      finarr<<temp
    end
    temp=[]
    num=num+1
  end
  return finarr
        
end


puts combine_anagrams(words)