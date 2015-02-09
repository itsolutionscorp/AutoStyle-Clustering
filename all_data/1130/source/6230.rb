def combine_anagrams(words)
hash_index = Hash.new{ |h,k| h[k] = [] }
arr_temp, arr_sort, arr_final, arr_group = [], [], [], []

arr_in = words.map{ |item| item.downcase }

#sort all chars in anagrams list and store
arr_in.each do |item|
 arr_sort << item.chars.sort.join
end
p arr_sort

#get all matching strings indexes from sorted array
arr_sort.each_with_index do |key, ind|
  hash_index[key] << ind
end     
p hash_index

#get values from original array for matched index positions and store
hash_index.each do |val|
  arr_temp = hash_index[val[0]]
  arr_temp.each do |i|
  arr_final << words[i]
end
arr_group << arr_final
arr_final = []
end           
return arr_group
end


