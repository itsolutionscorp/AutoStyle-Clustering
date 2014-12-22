def combine_anagrams(words)
  sample = words
  sorted = []
  sort(sample, sorted)
end

def sort(sample, sorted)
  word = sample[0]
  sample.delete(word)
  if word == nil 
    then return sorted 
  else 
    temp = [word]
    sample.each do |entry|
      if entry.split('').sort.join('') == word.split('').sort.join('')
        then temp = temp + [entry]; sample.delete(entry)
      end
    end
    sorted = sorted + [temp]
    sort(sample, sorted)
  end
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
