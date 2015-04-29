def group_anagrams(sorted, result_arr)
  w = sorted.shift
  anagram_arr = [w[0]]
  rest_arr = Array.new
  sorted.each_index do |i|
    if (w[1] == sorted[i][1]) then
      (anagram_arr << sorted[i][0])
    else
      (rest_arr << sorted[i])
    end
  end
  (result_arr << anagram_arr)
  return if rest_arr.empty?
  group_anagrams(rest_arr, result_arr)
end

def combine_anagrams(words)
  return [] if words.empty?
  sorted = Array.new
  words.each_index do |i|
    letter_array = words[i].upcase.scan(/./)
    letter_array.sort!
    sw = letter_array.to_s
    sorted[i] = [words[i], sw]
  end
  result_arr = []
  group_anagrams(sorted, result_arr)
  return result_arr
end

