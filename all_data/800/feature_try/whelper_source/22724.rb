def combine_anagrams(words)
  hash = {}
  words.each do |word|
    letters = word.downcase.split(//).sort.join
    hash[letters] = "#{word},#{hash[letters]}"
  end
  puts(hash.inspect)
  arr = []
  i = 0
  hash.each do |group|
    arr[i] = group[1].split(/,/)
    puts("arr #{i} = #{arr[i].inspect}")
    i = (i + 1)
    puts("#{group[1]} split = #{group[1].split(/,/).inspect}")
    puts(arr.class.to_s)
  end
  arr
end

