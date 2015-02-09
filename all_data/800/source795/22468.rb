def combine_anagrams(list)
	if list.empty? 
		list
	else
		keys = list.map {|w| asSortedListOfChar(w)}
		keys.uniq.map {|k| list.select {|w| asSortedListOfChar(w) == k}}
	end
end

def asSortedListOfChar(w)
	w.downcase.split(//).sort
end
