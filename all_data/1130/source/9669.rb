def combine_anagrams(words)
  h = Hash.new {|h,k| h[k] = [] }
  words.each do |w|
    key = w.downcase.chars.sort.join
    h[key] << w
  end
  puts h
  result = []
  h.each_value do |v|
    result << v
  end
return result
end
