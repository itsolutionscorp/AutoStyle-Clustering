def combine_anagrams(words)
  hash_ana = Hash.new([])
  words.each { |w| hash_ana[w.downcase.split(//).sort] += [w] }
  arr_ana = []
  hash_ana.each_pair { |k, v| arr_ana = (arr_ana + [v]) }
  arr_ana
end