def combine_anagrams(words)
  result = Array.new(0)
  notfound = true
  words.each do |myword|
    for i in (0..(result.length - 1)) do
      (notfound = true
      if (result[i][0].downcase.chars.sort.join == myword.downcase.chars.sort.join) then
        result[i][result.send(:[], i).length] = myword
        notfound = false
        break
      end)
    end
    if notfound then
      result[result.length] = Array.new(0)
      result[(result.length - 1)][0] = myword
    end
  end
  return result
end

