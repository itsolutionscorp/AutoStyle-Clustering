# input: 
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

class String
	def sort
		str = self.split(//) #split in an array of char
		return str.sort.join
	end
end

def combine_anagrams1(words)
	res = Array.new
	result = Array.new
	sortedWords = Hash.new
	words.map {|word|
		sortedWords.store(word, word.sort.downcase )
		}
	groups = sortedWords.values.uniq
	groups.each { |repr|
		sortedWords.each_pair { |key, value|
			if value == repr 
				res << key
			end
			}
		result << res
		res = []
		}	
	return result
end

def combine_anagrams(words)
	l = words.sort {|a, b| a.downcase.sort <=> b.downcase.sort} #orders by optional code block
	print l
	print "\n"
	result = []
	if not l.shift == nil #Si la lista no es vacía
		r = [l.shift] #toma el primer elemento de la lista
		print r
		print "\n"
		l.each {|b|
			if b.downcase.sort == r[0].downcase.sort
				r << b
			else 
				result << r
				r = [b]
			end
			}
		result << r
	end
	return result
end


print combine_anagrams ([] )