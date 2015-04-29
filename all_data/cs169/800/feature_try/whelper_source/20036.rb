def combine_anagrams(words)
  raise(NoElementsInArrayError) unless (words.length > 0)
  h = Hash.new
  words.each_index do |x|
    sorted_word = words.at(x).downcase.chars.sort.join
    h[sorted_word] = Array.new unless h.has_key?(sorted_word)
    h.fetch(sorted_word).push(words.at(x))
  end
  result = Array.new
  h.each_value { |value| result.push(value) }
  return result
end

