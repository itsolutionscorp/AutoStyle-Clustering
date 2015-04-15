def combine_anagrams(words)
  words.inject([]) do |arr, word|

    i = arr.index do |x| 
      x.first.downcase.chars.sort.join == word.downcase.chars.sort.join
    end

    if i.nil? then
      arr.push [word]
    else
      arr[i].push word
    end

    arr
  end
end


l = ['cars', 'for', 'potatoes', 'racs', 'four',
  'scar', 'creams', 'scream']

print combine_anagrams l
puts ''