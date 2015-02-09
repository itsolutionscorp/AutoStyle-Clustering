def combine_anagrams(words)
  hsh = Hash.new
  words.each do |str|
    reorder = str.downcase.scan(/./).sort.join
    if hsh.has_key?(reorder) then
      hsh[reorder] += [str]
    else
      hsh.store(reorder, [str])
    end
  end
  return hsh.values
end