def combine_anagrams(words)
  return words if (words.length == 0)
  sorts = []
  new_list = []
  flag = false
  words.each { |word| sorts.push(word.downcase.split("").sort) }
  new_list.push([words[0]])
  sorts.slice!(0)
  sorts.each_index do |i|
    new_list.each do |arr|
      if (sorts[i] == arr[0].downcase.split("").sort) then
        arr.push(words[(i + 1)])
        flag = false
        break
      end
      flag = true
    end
    (new_list << [words[(i + 1)]]) if (flag == true)
    flag = false
  end
  return new_list
end