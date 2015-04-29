def combine_anagrams(words)

  h = Hash.new

  words.each {|elt|
    key = elt.downcase.chars.sort.join
    if (h.has_key?(key))
      h[key] << elt
    else
      h[key] = Array[elt]
    end
  }

  retArray = Array.new
  h.each_value {|value|
    retArray << value
  }

  retArray
end



