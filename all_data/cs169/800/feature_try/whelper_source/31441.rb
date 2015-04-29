def combine_anagrams(words)
  ret = []
  words.each do |word|
    flag = 0
    ret.each do |x|
      xx = x[0].chars.sort { |a, b| a.casecmp(b) }.join
      wordx = word.chars.sort { |a, b| a.casecmp(b) }.join
      if (xx.casecmp(wordx) == 0) then
        (x << word)
        flag = 1
        break
      end
    end
    (ret << [word]) if (flag == 0)
  end
  return ret
end

