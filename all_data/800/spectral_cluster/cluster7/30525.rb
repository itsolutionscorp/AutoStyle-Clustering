	
def combine_anagrams(words)
arrayResult=[]
arrayGroupIndex =[]
arrayGroup =[]
wordsSort=[]



wordsSort=sort_words(words)

wordsMod = wordsSort.uniq




 wordsMod.each_index do |eltUniq|
arrayGroupIndex =[]
arrayGroup =[]


 	wordsSort.each_index do |elt|
 		if wordsMod[eltUniq].eql?(wordsSort[elt])
 			arrayGroupIndex << elt
 			
 		end
 	end

	arrayGroupIndex.each_index do |elt|
		
	eltResult = arrayGroupIndex[elt]
 		arrayGroup[elt] = words[eltResult]
 	
 	end
 	arrayResult << arrayGroup

 end

 arrayResult
	
end

def sort_words(words)
wordsSort=[]

	words.each_index do |word|
		wordsSort[word]=words[word].downcase.scan(/./).sort.join
	end
	wordsSort
end
#puts test = "cars".scan(/./).sort
#words =['cars', 'cars' ,'for', 'potatoes', 'racs', 'four','rouf','scar', 'creams', 'scream']
#words =['a', 'a', 'b', 'e', 'a',"e"]
#words=['A','a']
#puts "Result #{combine_anagrams(words)}"
# Result [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]





#array =["acrs", "for", "aeoopstt", "acrs", "foru", "acrs", "acemrs", "acemrs"]
#element = "acrs"
#puts repeat_array_index(element, array)
