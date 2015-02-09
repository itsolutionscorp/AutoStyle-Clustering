#part 3
def combine_anagrams(words)
  h = Hash.new
  words.each do |w|
    sort = w.downcase.chars.sort { |a, b| a.casecmp(b) }.join
    if h[sort] == nil
      h[sort] = [w]
    else
      h[sort][h[sort].length] = w
    end
  end
  return h.values
end

words = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).to_s

#exp output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
