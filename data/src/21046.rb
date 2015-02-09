def combine_anagrams(words)
new={}
words.each do |x|
  y=x.downcase.split(//).sort.join
  if new.has_key?(y) then new[y].concat([x])
  else new[y]=[x]
  end
  end
return new.values
end

