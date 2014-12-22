#Part 3: anagrams
def combine_anagrams(words)
  if words.length == 0
    Array.new
  else
    @iguales = Array[words.select {|w| words[0].downcase.split("").sort.join==w.downcase.split("").sort.join}]
    @diferentes = words.reject {|w| words[0].downcase.split("").sort.join.downcase==w.downcase.split("").sort.join.downcase}
    @iguales + combine_anagrams(@diferentes)
  end
end