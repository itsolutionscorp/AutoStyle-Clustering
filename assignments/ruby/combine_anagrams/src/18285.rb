def combine_anagrams(words)
  sort_w = Hash.new
  words.each do |w|
    w_sort = w.downcase.split("").sort.join.gsub(/(.)\1{2,}/) do |s|
      (s.length.to_s + s[0, 1])
    end
    if (not sort_w.has_key?(w_sort)) then
      sort_w[w_sort] = [w]
    else
      sort_w[w_sort] = sort_w[w_sort].push(w)
    end
  end
  out_array = Array.new
  sort_w.each_key { |ww| out_array.push(sort_w[ww]) }
  return out_array
end