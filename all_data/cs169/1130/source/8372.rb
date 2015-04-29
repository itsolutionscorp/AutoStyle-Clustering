def combine_anagrams(words)
  arr = words.clone
  arr.map! { |arri| arri = arri.downcase.split(//).sort.join
  }
  res = []
  j = 0
  black=[]
  arr.each_with_index { |arri, i|
    if !black.include?(arri)
      res[j] = []
      res[j].concat(words[i].split)
      arr.each_with_index { |arri1, y|
        if arri == arri1 && y!=i
          res[j].concat(words[y].split)
        end
      }
      j= j+1
      black.push(arri)
    end
  }
  res
end

p ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'].group_by{|i| i.downcase.split(//).sort.join}.values
