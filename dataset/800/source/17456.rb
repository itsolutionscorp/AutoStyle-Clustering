def combine_anagrams(theArr)
  answerArray = Array.new
  theArr.each do |outeritem|
    compare = outeritem.chars.sort.join
    answerArray << theArr.find_all do |item1|
      item1.chars.sort.join==compare
    end
  end 
  answerArray.uniq!
end
# 
# 
# theArr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# solve(theArr)
# answerArray=Array.new
# answerArray << theArr.find_all do |item1|
#   item1.chars.sort.join=='acrs'
# end
# print answerArray

  # no go through a and find the index of all match
  # #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"]
  # words_hash = words.each_with_object(Hash.new []) do |word, hash|
  #   hash[word.chars.sort] += [word]
  # end

  # 1.  get the first element, sort it, and use it as a basis of comparison.  