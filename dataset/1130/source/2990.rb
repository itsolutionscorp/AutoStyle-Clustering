def combine_anagrams(words)
  map = {}
  words.each { |w|
    ss = sorted(w)
    map[ss] = (map[ss] || [])
    map[ss]<<w
  }
  list = []
  map.keys.collect{|key|
    list<<map[key]
  }
  return list
end

def sorted(string)
  return string.downcase.chars.sort.join
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

p combine_anagrams(words)
