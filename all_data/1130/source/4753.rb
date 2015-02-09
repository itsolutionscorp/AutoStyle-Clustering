def combine_anagrams(words)
  res = {}
  words.map do |x|
     y = x.downcase.split(//).sort
     if res.has_key?(y) then
       res[y] = res[y] << x
     else
       res[y] = [x]
     end
  end
  res.values
end

