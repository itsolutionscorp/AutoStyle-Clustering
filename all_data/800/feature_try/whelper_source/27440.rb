def helper(thing)
  clean = thing.downcase.split(//)
  [clean.sort, thing]
end

def scan(scanner, strs)
  result = []
  strs.each { |s| result.push(getval(s)) if checker(scanner, s) }
  return result
end

def cleaner(scanner, strs)
  strs.each { |s| strs.delete(s) if checker(scanner, s) }
  return strs
end

def combine_anagrams(str)
  str.map { |d| helper(d) }
  if (str.length == 0) then
    []
  else
    [scan(str[0], str[1, str.length]), combine_anagrams(cleaner(str[0], str[1, str.length]))]
  end
end

