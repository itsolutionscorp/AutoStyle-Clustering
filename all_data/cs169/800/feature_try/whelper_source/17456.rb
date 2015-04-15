def combine_anagrams(theArr)
  answerArray = Array.new
  theArr.each do |outeritem|
    compare = outeritem.chars.sort.join
    (answerArray << theArr.find_all { |item1| (item1.chars.sort.join == compare) })
  end
  answerArray.uniq!
end

