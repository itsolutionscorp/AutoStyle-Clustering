def combine_anagrams(words)
list = []
if words.length > 0
list = find_matches(words[0], words)
end
  return list
end


def find_matches(word, words)
mainList = []
list1 =
 words.find_all{|obj| obj.downcase.gsub(/\w/).sort == word.downcase.gsub(/\w/).sort }
 mainList.push(list1)

newList = 
words.reject{|obj| obj.downcase.gsub(/\w/).sort == word.downcase.gsub(/\w/).sort }
  if newList.length > 0
    list2 = find_matches(newList[0], newList)
    mainList.concat(list2)
  end
    return mainList
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

puts combine_anagrams([])
