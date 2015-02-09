def combine_anagrams(words)
  polje=[]
  an=Hash.new
  nasao=false
  for w in words
    p=w.downcase.unpack("c*").sort.pack("c*")
    #p=p.downcase
    if an.has_key?(p)
      pom=an[p]
      pom<<w
      an[p]=pom
    else
      an[p]=[w]
    end
  end
  rez=[]
  an.each do|k,polje|
    rez << polje
  end
  return rez 
  #   <YOUR CODE HERE>
end
a=['cars', 'HEllo', 'hello','FOR', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'hELLO', 'olleh'] 
print combine_anagrams(a)
