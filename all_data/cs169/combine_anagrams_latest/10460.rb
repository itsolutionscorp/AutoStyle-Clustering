def combine_anagrams(words)
  
  h = Hash.new
  r = Array.new
  
  # words.map { |w| h[w.downcase.chars.sort.join] = []}
  # words.map { |w| h[w.downcase.chars.sort.join] << w}

  # h.each_value{|value| r << value}
  # r
  
  words.each do |w|
    if h[w.downcase.chars.sort.join] == nil then h[w.downcase.chars.sort.join] = [w]
    else  h[w.downcase.chars.sort.join] << w
    end
  end
  
  h.each_value{|value| r << value}
  r
  
end

x = ['cars', 'FOR', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
p combine_anagrams(x)
