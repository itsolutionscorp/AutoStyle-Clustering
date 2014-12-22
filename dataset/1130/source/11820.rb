
def combine_anagrams(words)
  sorted = Hash.new
  words.each { |s|
    key = s.downcase.chars.sort.join
    if sorted.has_key? key
      sorted[key] = sorted[key] + [s]
    else
      sorted[key] = [s]
    end
  }
  sorted.values
end

# puts combine_anagrams(['cars', 'for', 'potatoes', 'raCs', 'four','scar', 'creams', 'scream'])
