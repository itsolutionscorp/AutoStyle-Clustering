require 'pp'

def combine_anagrams(words)
	map = {}
	
	words.each do |w|
	  puts "w: " + w
	  sorted = w.chars.sort.join
	  puts "sorted: " + sorted
	  map[sorted] = [] if map[sorted].nil?
	  map[sorted] = map[sorted] + [w]
	  puts "added to: " + map[sorted].length.to_s
	  #map[sorted] = map[sorted] + w
	end
	
	result = []
	
	map.each do |key|
	  pp(key)
	  puts "key[1]: "
	  pp(key[1])
	  result = result + [key[1]]
	end
	
	result

end


w = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
pp( combine_anagrams(w))