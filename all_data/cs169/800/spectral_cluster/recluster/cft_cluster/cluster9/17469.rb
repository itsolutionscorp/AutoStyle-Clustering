def combine_anagrams(words)

if words.size == 0
	return words
end

hash = Hash.new("Sorted")
words.each { |x|  hash[x] = x.downcase.unpack("c*").sort.pack("c*") };

a = []
a = hash.values.uniq

retArray = Array.new
# puts a
# puts hash
# puts hash.values

for i in 0..a.size-1
	h2 = hash.select {|k,v| v == a.at(i) };
	# puts " ------- "
	# puts h2
	# puts h2.keys
	# puts " ------- "
	retArray.push( h2.keys );
end

return retArray
end
