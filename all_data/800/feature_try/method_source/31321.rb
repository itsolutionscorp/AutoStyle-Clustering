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