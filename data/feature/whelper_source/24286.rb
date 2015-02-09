def combine_anagrams(words)
  rlt = []
  words.each do |w|
    flag = false
    rlt.each do |r|
      if (w.downcase.sum == r[0].downcase.sum) then
        r.push(w)
        flag = true
        break
      end
    end
    rlt.push([w]) if (flag == false)
  end
  return rlt
end

