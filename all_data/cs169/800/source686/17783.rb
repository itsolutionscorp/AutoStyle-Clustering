
def combine_anagrams(words)
  hash = words.inject(Hash.new([])) {|h,v| h[v.downcase.chars.sort.join] += [v]; h }
  
  return hash.values
  #downcase, sort, add 
end

if __FILE__ == $0
  #code that is executed when script is ran
end

