# input:
input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  buckets =  Hash.new([])
  words.each do | item |
      key =  item.downcase.chars.sort.join

    if buckets.has_key?(key)
      buckets[key] << item
    else
      buckets[key] = [item]
    end
  end

  output =  Array.new;
  buckets.each do | key, value |
     output  << value
  end
  return output
end

