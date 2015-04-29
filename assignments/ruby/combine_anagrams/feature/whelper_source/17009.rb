def combine_anagrams(words = [])
  output = Hash.new
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    output[sorted].nil? ? (output[sorted] = [word]) : (output[sorted] += word)
  end
  return output.values
end

