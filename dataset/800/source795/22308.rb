def anagrams? (x, y)
  x.downcase.split(//).sort.join == y.downcase.split(//).sort.join 
end

def group_anagrams(w, words)
  array=[]
  words.each do |x|
    if anagrams? w, x 
      array.push x 
    end
  end
  return array
end

def combine_anagrams(words)
  array=[]
  words.each do |x|
    array.push group_anagrams x, words
  end
  array.uniq.sort
end
