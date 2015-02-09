def combine_anagrams(words)
  wh = Hash.new
  
  words.each do |w|
    wa = Array.new
    w.each_char do |c|
      wa.push(c.downcase)
    end
    wa.sort!

    wo = ''
    wa.each do |c|
      wo << c
    end

    if wh.has_key? wo
      wh[wo].push w
    else
      wh[wo] = [w]
    end
  end

  x = Array.new
  wh.each_value do |v|
    x.push v
  end

  x
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])

