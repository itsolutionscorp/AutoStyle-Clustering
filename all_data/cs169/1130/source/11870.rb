def combine_anagrams(words)
  result = []
  words.each do |w|
    sorted = w.downcase.chars.sort.join
    if result.length == 0
      result.push [w]
    else
      found = false
      result.each do |x|
        if sorted == x[0].downcase.chars.sort.join
          x.push w
          found = true
        end
      end
      result.push [w] unless found
    end
  end
  result
end
