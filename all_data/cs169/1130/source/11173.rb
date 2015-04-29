def bwtest 
  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
end

def combine_anagrams(words)
  output = []
  words.each do |current| 
    if !output.join.include? current 
      tmp = words.select { |w| w.downcase.chars.sort.join == current.downcase.chars.sort.join }
      output << tmp
    end
  end
  output
end
