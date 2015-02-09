def combine_anagrams(words)
  groups = Hash.new
  words.each do |w|
    k = w.downcase.chars.sort.join
    if groups[k] == nil
      groups[k] = [ w ]
    else
      groups[k] << w
    end
  end

  result = []

  groups.each_pair do |k, v|
    result << v
  end

  return result
end

