
def combine_anagrams(words)

	groups = Hash.new;

	words.each do |word|

		key = word.downcase.split('').sort.join('');

		groups[ key ] = Array.new unless groups[ key ];
		groups[ key ].push(word);

	end

	return groups.values;

end

