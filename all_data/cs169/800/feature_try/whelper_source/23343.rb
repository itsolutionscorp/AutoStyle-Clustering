def combine_anagrams(words)
  array = words
  anagram = Hash.new
  array.each do |string|
    letters1 = string.downcase.split(//).sort.join
    "string = #{string} letters1 = #{letters1}"
    anagram[letters1] = "#{anagram[letters1]} #{string}"
    puts(letters1)
  end
  final = Array.new
  x = 0
  anagram.each do |string|
    final[x] = string[1].split
    x = (x + 1)
  end
  final
end

