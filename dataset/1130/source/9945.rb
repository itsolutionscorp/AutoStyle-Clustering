def combine_anagrams(words)
  anagram = Array.new
  sorted = Array.new
  words.each do |x|     
    if sorted.include?(x.downcase.chars.sort.join)
      index = 0
      anagram.each do |y|
        if y[0].downcase.chars.sort.join==x.downcase.chars.sort.join
          anagram[index] += [x]
        end
        index += 1
      end
    else
      sorted += [x.downcase.chars.sort.join]
      anagram += [[x]]
    end
  end
  anagram
end

# combine_anagrams(["cars","for","potatoes","racs","four","scar","creams","scream"])

