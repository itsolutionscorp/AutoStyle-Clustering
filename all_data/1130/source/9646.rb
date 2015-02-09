# Part 3: anagrams

def combine_anagrams(words)
	dict = {}
  words.each{ | p_word | 
  	a_word = []
  	p_word.each_char { |chr| a_word << chr.upcase }
  	a_word.sort!
  	k_word = ''
    a_word.each{ | chr | k_word += chr }
    # puts p_word + ' --> ' + k_word
    dict[k_word] ||= []
    dict[k_word] << p_word
  }
  dict.values
end

my_words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
puts combine_anagrams(my_words)

