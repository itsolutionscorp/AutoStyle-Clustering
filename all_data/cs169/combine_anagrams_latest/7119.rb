def combine_anagrams(words)

  results = []
  ar_iter = 0 
  number  = words.size

  while number != 0
    results[ar_iter] = Array.new
    word   = words.first.downcase.chars.sort.join

    n = 0
    words.size.times do |number|
      if words[number] == words.first
        n += 1 
      end
    end
    
    n.times do
      results[ar_iter] << words.first
    end

    words  = words - words.first.split
    number-= n

    number2 = words.size - 1
    while number2 >= 0    
      if words[number2].downcase.chars.sort.join == word
        results[ar_iter] << words[number2]
        words  = words - words[number2].split
        number -= 1 
      end
      number2 -= 1
    end

    ar_iter += 1
  end

  return results
end

game =  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'cars']
p combine_anagrams(game)

