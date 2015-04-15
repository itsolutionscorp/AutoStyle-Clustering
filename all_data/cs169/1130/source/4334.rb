def combine_anagrams(words)
  wds = words.map { |elt| elt.downcase.split("").sort.join }.uniq
  output = []
  wds.each do |wd|
    temp = []
    words.each do |word|
      temp << word if wd == word.downcase.split("").sort.join
    end
    output << temp
  end
  return output
end