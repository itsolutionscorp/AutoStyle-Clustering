

def combine_anagrams(words)
  # Use hash
  ana_groups = Hash.new([])

  words.each do |word|
    norm_word = word.downcase.chars.sort.join
    ana_groups[norm_word] = ana_groups[norm_word] + [word] 
  end

  # convert hash to array
  ana_groups.map{ |k, v| v}
end

if __FILE__ == $0
  words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 
    'creams', 'scream']
  puts combine_anagrams(words).to_s
end
