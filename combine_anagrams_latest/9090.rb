puts "\nHomework 1 - Part 3"


def combine_anagrams(words)
  hash = words.group_by do |w|
    w.downcase.chars.sort.join
  end

  hash.values
end


puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
