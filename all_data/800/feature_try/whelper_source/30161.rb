def combine_anagrams(words)
  hk = []
  words.each { |i| hk.push(i.downcase.chars.sort.join) }
  ret = []
  num = []
  words.length.times do |i|
    if (ret.length == 0) then
      num.push(ret.length)
      ret.push([words[i]])
      next
    end
    find = 0
    words.length.times do |j|
      break if (j == i)
      if (num[j] != -1) and (hk[j] == hk[i]) then
        find = 1
        ret[num[j]].push(words[i])
        num.push(-1)
        break
      end
    end
    if (find == 0) then
      num.push(ret.length)
      ret.push([words[i]])
    end
  end
  return ret
end

