def combine_anagrams(words)

  hash = Hash.new()

  words.each do |str|
    key = str.downcase.chars.sort.join
    print( "str: <", str, "> key: ", key, "\n")
    list = hash[key]
    print( "list: ", list.inspect, "\n" )
    if ( list == nil )
      hash[key] = [ str ]
    else
      list << str
    end
  end

  hash.values
end


puts combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] ).inspect
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
