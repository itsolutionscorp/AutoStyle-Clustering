def combine_anagrams(words)
  retval = {}
  words.each do |inpval|
    lcanag = inpval.downcase.chars.sort.join
    if (retval[lcanag] == nil) then
      retval[lcanag] = [inpval]
    else
      (retval[lcanag] << inpval)
    end
  end
  retarray = []
  retval.each { |s, el| (retarray << el) }
  return retarray
end