class NoElementsInArrayError < StandardError ; end

  def combine_anagrams(words)
  raise NoElementsInArrayError unless words.length > 0

  h = Hash.new
  words.each_index do |x|
    sorted_word = words.at(x).downcase.chars.sort.join  # ==> [acrs, for, aeoopstt, acrs, foru, acrs, acemrs, acemrs]
    unless h.has_key?(sorted_word)                      # creates an empty new array as value for key "acrs", "for" and so on...
      h[sorted_word] = Array.new()
    end

  h.fetch(sorted_word).push(words.at(x))              # populates the array for key "acrs", "for" and so on...
  end

  result = Array.new
  h.each_value {|value| result.push(value) }
  return result

end

