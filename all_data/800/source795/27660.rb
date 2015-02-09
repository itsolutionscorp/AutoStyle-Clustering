def combine_anagrams(myarray)
  counts=hash.new{|hash, key| hash[key]=a}
  myarray.each do |i|
  a=myarray[i].sort
  counts[i] += 1
  end
  end