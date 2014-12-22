def combine_anagrams(words)
  result = []
  until words.empty? do
    (new_arr = ([] << words[0])
    for i in (1..(words.length - 1)) do
      (new_arr << words[i]) if (toSortArr(new_arr[0]) == toSortArr(words[i]))
    end
    (result << new_arr)
    words = (words - new_arr))
  end
  result
end