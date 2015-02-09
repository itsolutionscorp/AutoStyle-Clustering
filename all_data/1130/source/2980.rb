
def combine_anagrams(words)
  result = []
  table = {}
  n = 0

  words.each do |w|
    value = w.downcase.split(//).sort.join()
    if table[value].nil?
       table[value] = n
       result[n] = []
       n = n + 1
       end
    result[table[value]] << w
  end

  result
end

