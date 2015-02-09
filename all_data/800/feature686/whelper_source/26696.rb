def combine_anagrams(words)
  temp = Array.new
  words.map do |i|
    sorted_words = i.downcase.split(//).sort.join
    temp.push(sorted_words)
  end
  result = Array.new
  for i in (0..(words.length - 1)) do
    (result.push([])
    for j in (0..(words.length - 1)) do
      result[i].push(words[j]) if (temp[i] == temp[j])
    end)
  end
  return result.uniq
end

