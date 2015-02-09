def combine_anagrams(words)
  #dc_hash = Hash.new(0)
  #words.each { |word| dc_hash[word.downcase] = word }
  #
  #words_dc = Array.new
  #words.each { |word| words_dc.push word.downcase }
  #
  #la = 'Aa'.downcase.chars.sort.join
  #
  #order_hash = Hash.new(Array.new())
  #words_dc.each do |word|
  #   order_hash[word.chars.sort.join] += [dc_hash[word]]
  #end
  #
  #output = Array.new
  #order_hash.each do |group|
  #  output.push group[1]
  #end
  #
  #return output

  hash = Hash.new(Array.new())
  words.each do |word|
    hash[word.downcase.chars.sort.join] += [word]
  end

  output = Array.new
  hash.each do |group|
    output.push group[1]
  end

  return output

end

puts combine_anagrams(['a', 'A'])

puts combine_anagrams(['cars', 'for', 'Potatoes', 'racs', 'four','scar', 'creams', 'scream'])

puts combine_anagrams(['cars', 'for', 'potatoes', 'ROF', 'racs', 'four','scar', 'creams', 'scream'])


