def combine_anagrams(words)
    group = {}
    words.each do |s|
      key = s.downcase.chars.sort
      if (group[key] == nil) then
          group[key] = [s]
      else
          group[key] = group[key] << s
      end
    end
    arrayed = []
    group.each_value do |v| arrayed << v end
    arrayed
end

#print(combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']))

