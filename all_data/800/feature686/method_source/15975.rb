def combine_anagrams(words)
  Array((anagrams = []))
  words.each do |x|
    flag = false
    anagrams.collect do |y|
      if (x.downcase.chars.to_a.sort == y[0].downcase.chars.to_a.sort) then
        (y << x)
        flag = true
        break
      end
    end
    (anagrams << [x]) unless flag
  end
  anagrams
end