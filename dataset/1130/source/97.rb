def canonize w
  t = []
  w.each_char{ |c|
    t << c
  }
  t.sort!.join
end

def combine_anagrams(words)
  groups = {}
  words.each { |w|
    w.downcase!
    wc = canonize w

    group = groups[wc] ? groups[wc] : []
    group << w
    groups[wc] = group
  }
  groups.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams',
'scream']).inspect
