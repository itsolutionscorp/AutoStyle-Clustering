# input:
input=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  #h=Hash.new(0)
  output = Array.new
  words.map{|word| word.chars.sort.join }.uniq.each {|anagram|
    w = words.partition { |word| word.chars.sort.join==anagram}
#    puts "w : " + w.to_s
    output += [w[0]]
#    puts "output : " + output.to_s
  }
  return output
end

#puts combine_anagrams(input).to_s
