def combine_anagrams(words)
  result = []
  sorted = words.collect { |x| x.downcase.chars.sort.join }
  words.each do |word|
    insert = false
    result.each do |rlist|
      if rlist.collect { |x| x.downcase.chars.sort.join }.include?(word.downcase.chars.sort.join) then
        (rlist << word)
        insert = true
        break
      end
    end
    (result << [word]) unless (insert == true)
  end
  return result
end