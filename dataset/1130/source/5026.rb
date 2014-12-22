def strsort(str)
  return str.downcase.chars.sort.to_s()
end


def combine_anagrams(words)
  map = Hash.new

  words.each { 
    |word|
    sorted = strsort(word)
    if(map[sorted].nil?)
      a = Array.new
      a << word
      map[sorted] = a
    else
      map[sorted] << word
    end
  }
  return map.values
end

#a = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

#puts combine_anagrams(a).inspect 