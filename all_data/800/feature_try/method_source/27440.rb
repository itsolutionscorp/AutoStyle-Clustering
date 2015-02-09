def combine_anagrams(str)
  str.map { |d| helper(d) }
  if (str.length == 0) then
    []
  else
    [scan(str[0], str[1, str.length]), combine_anagrams(cleaner(str[0], str[1, str.length]))]
  end
end