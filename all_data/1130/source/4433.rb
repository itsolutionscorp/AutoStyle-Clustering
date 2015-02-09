def output_results(all_group_array)
	my_values = Array.new
	my_keys = Array.new
	my_group = Array.new
	my_groups = Array.new

	all_group_array.sort.reverse.each do |k,v|
	my_keys.push(k)
	my_values.push(v)
	end	

	for i in my_keys.uniq.sort do
		all_group_array.each do |k,v|
			if k == i then my_group.push(v) end
		end
		my_groups.push(my_group)
		my_group = []
	end
	return my_groups
end

def print_hash_fingerprint(your_hash)
	my_fingerprint = String.new
	
	# fingerprint builder
	your_hash.each do |key, value|
		my_fingerprint << "" << "#{key}"
	end
	your_hash.each do |key, value|
		my_fingerprint << "" << "#{value}"
	end
	return my_fingerprint
end

def count_words_chars(your_word)
	my_hash = Hash.new
	your_word.split(//).each { |k|
		if my_hash.has_key?(k)
			my_hash[k] = my_hash[k] + 1
		else
			my_hash[k] = 1
		end
	  }
	  return my_hash
end

def combine_anagrams(words)
	words = words.to_s.chomp.strip.downcase.gsub(/[^a-z\s+]+/i, '')
	
	my_array = Array.new
	
	hash_to_sort = Hash.new
	hash_sorted = Hash.new
	
	hash_to_sort = count_words_length(words)
	hash_sorted = hash_to_sort.sort{|k,v| 
		k[1]<=>v[1]
	}
	
	old = nil
	hash_sorted.each {|k,v| 
		if (old != v)
			old = v
			my_array.push(old)
		elsif (old == v)
			old = v
		end
	}
	
	your_word = String.new
	fingerprint = Array.new
	fingerprint_group_array = Array.new
	my_keys = Array.new
	
	h_sort = Hash.new
	h_coded = Hash.new
	
	my_array.each_index { |i| 
		#puts my_array[i]
		your_word = hash_sorted.each{ |k,v| 
			if (my_array[i] == v) then
				h = count_words_chars(k)
				h_sort = Hash[h.sort]
				fingerprint = print_hash_fingerprint(h_sort).split(" ")
				fingerprint = fingerprint, k
				#my_keys.push(k)
				fingerprint_group_array.push(fingerprint)
			end
		}
	}

	#return fingerprint_group_array
	#print "The groups of word anagrams are gather as follow: \n"
	return output_results(fingerprint_group_array)
end

def count_words_length(your_string)
	my_hash = Hash.new
	your_string.split.each { |k|
		word_length = k.length
		my_hash[k] = word_length
	 }
	 return my_hash
end

your_input = Array.new
your_input = gets

# string normalization .chomp.strip
my_original_values = Array.new
my_original_values = your_input 
#your_input = your_input.chomp.strip
 
#fingerprint_group_array = Hash.new
print all_group_array  = combine_anagrams(your_input)#.sort

#Example:
# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
##["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
## letters, keeping in mind that upper vs lowercase doesn't matter
