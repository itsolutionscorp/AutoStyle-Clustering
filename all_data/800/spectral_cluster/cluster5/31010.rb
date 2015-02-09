def combine_anagrams(words)

  ar=Array.new

  words.map{ |unique| unique.chars.sort.join.downcase }.uniq.map do |comp|

    ar.push(words.select{ |element| element.chars.sort.join.downcase == comp})

  end

  print ar

end