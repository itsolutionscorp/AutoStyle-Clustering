def combine_anagrams(words)

  hsh = Hash.new
  arry = Array.new
  words.each { |x|
    expld = x.downcase.chars.to_a.sort
    if hsh[expld] == nil
      hsh[expld] = arry.length
      arry[arry.length] = [ x ]
    else
      arry[hsh[expld]] += [ x ]
    end
  }
  arry
end
