require 'pp'

class String

  def permutation(&block)
    arr = split(//)
    arr.permutation { |i| yield i.join }
  end
end

def combine_anagrams (words)
  array_anagram = Array.new()
  words2 = words
  words.each do |w|
    arrtmp = Array.new()
    arrtmp.insert(0,w)
    w.to_s.permutation do |i|
      if words2.include?(i)
        arrtmp.insert(arrtmp.length,i)
        words2.delete(w)
      end
    end
    array_anagram.insert(array_anagram.length,arrtmp.uniq)       
  end
  return array_anagram  
end

#pp combine_anagrams ['cars', "for", 'potatoes', 'racs', 'four','scar', 'creams', 'scream']


