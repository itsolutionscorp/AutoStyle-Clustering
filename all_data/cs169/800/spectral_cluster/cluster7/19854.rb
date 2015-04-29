
def combine_anagrams(words)

  sortedwords = Array.new
  uniquewords = Array.new
  answer = Array.new
  partanswer = Array.new
  words.each do |word|
    sortedwords << word.downcase.split('').sort.join
  end


  uniquewords = sortedwords.uniq
  uniquewords.each do |word|
    matches = sortedwords.count(word)
    partanswer = []
    matches.times do
      matchindex = sortedwords.index(word)
      partanswer << words[matchindex]
      sortedwords.delete_at(matchindex)
      words.delete_at(matchindex)
    end
    answer << partanswer

  end
  return answer
end
