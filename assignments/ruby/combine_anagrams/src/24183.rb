def combine_anagrams(words)
  hash = Hash.new
  index = 0
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    if hash.has_key?(sorted) then
      hash[sorted].insert(0, index)
    else
      hash[sorted] = Array[index]
    end
    index = (index + 1)
  end
  answer = Array[]
  hash.each do |key, value|
    set = Array[]
    value.each { |place| set.insert(0, words[place]) }
    answer.insert(0, set)
  end
  return answer
end