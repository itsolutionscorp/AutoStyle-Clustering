def combine_anagrams(words)
  ana_list = []
  sorted_list = []
  words.each do |word|
    word = word.downcase
    temp_list = word.scan(/./)
    temp_list.sort!
    sorted_list.push(temp_list)
  end
  for i in (0..(sorted_list.size - 1)) do
    (exist = false
    ana_list.each do |item|
      if item.include?(words[i]) then
        exist = true
        break
      end
    end
    if (not exist) then
      temp_list = []
      for j in (i..(sorted_list.size - 1)) do
        temp_list.push(words[j]) if (sorted_list[i] == sorted_list[j])
      end
      ana_list.push(temp_list)
    end)
  end
  return ana_list
end

