def combine_anagrams(words)
result = [];
i = 0;

while words.size != 0
	a = words.find_all{|y| words[0].downcase().chars.sort.join == y.downcase().chars.sort.join};
	result.push(a);
	words = words - a;
end

return result;
end
