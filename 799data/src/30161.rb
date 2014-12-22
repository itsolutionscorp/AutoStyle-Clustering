def combine_anagrams(words)
  hk = []
  words.each do |i|
    hk.push(i.downcase.chars.sort.join)
  end
  #print hk
  ret = []
  num = []
  words.length.times do |i|
    if ret.length == 0
      num.push(ret.length)
      ret.push([words[i]])
      next
    end
    find = 0
    words.length.times do |j|
      if j == i 
        break
      end
      if num[j] != -1 && hk[j] == hk[i]
        find = 1
        ret[num[j]].push(words[i])
        num.push(-1)
        break
      end
    end
    if find == 0
      num.push(ret.length)
      ret.push([words[i]])
    end
  end
  return ret
end

hk = [[1,2],[3,4]]
hk.push([1,2])
hk[0].push(3)
#print hk
input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

print combine_anagrams(input)






















