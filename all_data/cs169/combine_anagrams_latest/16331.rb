
def combine_anagrams(words)
  output = Array.new
  words.each do |s|
    matchfound = false
    if output.length > 0 then
        for i in 0..output.length
          if output[i].class == Array and output[i].length > 0 and output[i][0].upcase.chars.sort.join == s.upcase.chars.sort.join then
            output[i] += [s]
            matchfound = true
          end
        end
    end
    if matchfound == false then
      output += [ [ s ] ]
    end       
  end
  return output
end
combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])