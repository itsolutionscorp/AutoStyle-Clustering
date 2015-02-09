def combine_anagrams(iparr)
  anagram_groups = []
  duparr = []
  print("The input array is empty\n") if (iparr.length == 0)
  iparr.each { |element| (duparr << element.downcase.chars.sort.join) }
  h = Hash.new { Array.new }
  duparr.each_with_index { |x, index| h.store(x, (h[x] << index)) }
  h.each do |key, value|
    templist = []
    value.each { |index| (templist << iparr[index]) }
    (anagram_groups << templist)
  end
  return anagram_groups
end

