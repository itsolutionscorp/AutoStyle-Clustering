def combine_anagrams(words)
  output = []
  workRow = words.map { |item| item.downcase.chars.sort.join }.uniq
  nbInnerRow = workRow.count
  workRow.each do |item|
    partial = []
    words.each do |wd|
      if (item.downcase.chars.sort == wd.downcase.chars.sort) then
        partial[partial.length] = wd
      end
    end
    output[output.size] = partial
  end
  return output
end