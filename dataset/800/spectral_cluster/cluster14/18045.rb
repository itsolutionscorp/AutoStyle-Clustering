#!/usr/bin/env ruby



def combine_anagrams(iparr)
	anagram_groups = []
	duparr = []
	if iparr.length==0
		print "The input array is empty\n"
	end
	iparr.each{|element|
		duparr << element.downcase.chars.sort.join }
	h = Hash.new{Array.new}
	duparr.each_with_index{|x,index| h.store(x,h[x]<<index) }
	h.each{|key, value| 
		templist=[]
		value.each{|index| templist<<iparr[index]}
		anagram_groups<<templist
		}
	
	return anagram_groups
end
#groups=combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scaR', 'creaMs', 'Scream'])
