def combine_anagrams(words)
  sortwords = Array.new
  words.each do |word|
    sortwords.push(word.downcase.split(//).sort.join)
  end
  sortwords = sortwords.uniq
  result = Array.new
  sortindex = 0
  sortwords.each do |sortword|
    result.push(Array.new)
    words.each do |word|
      if sortword == word.downcase.split(//).sort.join
        result[sortindex].push(word)
      end
    end
    sortindex+=1
  end
  return result
end
