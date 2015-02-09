# calculate word hash (sorted letters without case)
def ana(string)
  return string.chars.sort { |a, b| a.casecmp(b) }.join.downcase
end

src = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
src = ['cars', 'for', 'potatoes', 'racS', 'four','scar', 'creams', 'scream']


def combine_anagrams(words)
  
  # temp hashtable
  res=Hash.new([])
  
  # group source words by their hash (sorted letters) in arrays
#  words.each {|word| 
#    el=ana(word); 
#    if res[el].length==0;
#      res[el]=[word];
#    else 
#      res[el]<<word;
#    end;
#  }

  # shorter version
  words.each {|word| 
    el=ana(word)
    (res[el].length==0 ? res[el]=[word] : res[el]<<word)
  }

  # combine arrays from hashtable into resulting array
  res_ar=[]
  res.each { |ar| res_ar << ar[1]}
  
  return res_ar
end

combine_anagrams(src)
