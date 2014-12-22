def combine_anagrams(words)
  #   For each word, loop over all other words and compare.  Turn each word lower case and sort
  ana_array = []
  words.each do |w|
    temp_arry = []
    words.each do |in_w|
      if w.downcase.chars.sort == in_w.downcase.chars.sort
        temp_arry.push(in_w)
      end
    end
    ana_array.push(temp_arry)
  end
  return ana_array.uniq
end

if __FILE__ == $0
  l1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  combine_anagrams(l1).each do |e|
    puts "Anagrams: "
    e.each do |e_ana|
      puts "#{e_ana} "
    end
    puts "\n"
  end
  
end