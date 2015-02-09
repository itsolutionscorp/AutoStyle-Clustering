# HW 1: Ruby calisthenics
# Part 3: Anagrams
# David.Allan@finch.org
# 3rd March 2012

# ----------------------------------------------------------------------

def combine_anagrams(words)
	hash = Hash.new;
	hash.default = [];
	words.each do |s|
		q = s.downcase.split("").sort { |a,b| a <=> b } .join("");
		hash[ q ] = hash[ q ] +  [ s ];
		end
	res = Array.new;
	hash.each do |k,v|
		res = res + [ v ];
		end
	res;
end

# ----------------------------------------------------------------------

puts combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] );

# ----------------------------------------------------------------------

