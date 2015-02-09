def combine_anagrams(words)
	(words.inject(Hash.new{|h, k| h[k] = []}) {|h, i| h[i.chars.sort_by(&:downcase).join] << i; h}).values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']).inspect
