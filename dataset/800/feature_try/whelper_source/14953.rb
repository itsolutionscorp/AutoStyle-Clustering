def count_letters(str)
  rex = Regexp.new(/(\w)/i)
  result = Hash.new
  str.scan(/(\w)/i).each do |s|
    letter = s[0].downcase
    if result.has_key?(letter) then
      result[letter] = (result[letter] + 1)
    else
      result[letter] = 1
    end
  end
  return result
end

def combine_anagrams(words)
  letter_counts = Hash.new
  words.each do |word|
    value = count_letters(word)
    if letter_counts.has_key?(value) then
      letter_counts[value].push(word)
    else
      letter_counts[value] = [word]
    end
  end
  result = []
  letter_counts.each_value { |value| result.push(value) }
  return result
end

