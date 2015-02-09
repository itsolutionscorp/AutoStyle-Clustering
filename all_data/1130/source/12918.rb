


def anagram?(s1,s2)
    
      return s1.downcase.split("").sort.to_s==s2.downcase.split("").sort.to_s
 

end


#@puts anagram?("cars", "scars")

t=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

def combine_anagrams(a)
  return a.map { |y|  a.select { |x| anagram?(y,x) } }.uniq
end


puts combine_anagrams(t)
