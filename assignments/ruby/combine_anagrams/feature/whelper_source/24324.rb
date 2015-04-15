def combine_anagrams(words)
  sWords = Hash.new
  words.each do |x|
    x.downcase!
    y = x.chars.sort.join
    sWords[y] = x
  end
  finalArray = Array.new
  j = 0
  sWords.each_key do |k|
    fWords = Array.new
    nWords = Array.new
    i = 0
    words.each do |w|
      if (k == w.chars.sort.join) and (k.length == w.length) then
        fWords[i] = w
        nWords[i] = fWords
        i = (i + 1)
      end
    end
    finalArray[j] = nWords.uniq.flatten
    j = (j + 1)
  end
  puts(finalArray.inspect)
  return finalArray
end

