def combine_anagrams(words)
  h = Hash.new
  words.each do |string|
    if h.has_key?(string.downcase.chars.sort.join) then
      h.fetch(string.downcase.chars.sort.join).push(string)
    else
      h[string.downcase.chars.sort.join] = Array.new
      h.fetch(string.downcase.chars.sort.join).push(string)
    end
  end
  result = Array.new
  h.each_value { |value| result.push(value) }
  return result
end

