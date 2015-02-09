def combine_anagrams(words)
  result = Hash.new
  words.each do |word|
    sortedword = word.downcase.chars.sort.join
    tmp = result[sortedword]
    if (tmp != nil) then
      puts((("adding word \"" + word) + "\" to hash"))
      result[sortedword] = (tmp + [word])
    else
      puts((("creating new hash entry with word \"" + word) + "\""))
      result[sortedword] = [word]
    end
  end
  return result.values
end