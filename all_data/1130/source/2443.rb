def combine_anagrams(words)
  table = Hash.new

  words.each do |x|
    sorted = x.downcase
    sorted = sorted.split(//)
    sorted = sorted.sort
    sorted = sorted.join

    #sorted = x.downcase.chars.sort.join   # sort chars of the given string alphabetically

    if table[sorted] == nil
      table[sorted] = [x]
    else
      table[sorted] << x
    end

    #table[sorted] = table[sorted] == nil ? table[sorted] = [x] : table[sorted] << x
  end

  return table.collect { |x,y| y }
end