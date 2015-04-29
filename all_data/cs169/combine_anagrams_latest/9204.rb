
def combine_anagrams(words)
  groups=Hash.new(0)
  for word in words:
    key=word.downcase().split("").sort()
    if groups[key]==0 then groups[key]=[] end
    groups[key]+=[word]
  end
  groups.values()
end

