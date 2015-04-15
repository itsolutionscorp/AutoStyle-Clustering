def combine_anagrams words
  memo = {}
  words.each do |word|
    key = word.downcase.split('').sort.join
    if memo[key]
      memo[key] << word
    else
      memo[key] = [word]
    end
  end
  memo.values
end
