def combine_anagrams(words)
  result = []
  for i in 0...words.length
    new_entry = true
    for j in 0...result.length
      if result[j][0].downcase.chars.sort.join == words[i].downcase.chars.sort.join
        #puts ("Added entry " + words[i])
        result[j] << words[i]
        new_entry = false
      end
    end
    if new_entry
      #puts ("New Entry " + words[i])
      result << []
      result[result.length - 1] << words[i]
    end
  end
  result
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

def count_words(string)
  string = string.downcase
  h = {}
  array = string.split(/\W+/).uniq.
  each do |s|
    h.merge!(s => string.scan(/\b#{s}\b/).length)
  end
  h
end
