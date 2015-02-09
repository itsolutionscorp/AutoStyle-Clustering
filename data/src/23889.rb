def combine_anagrams(words)
  aWrd = Hash.new
  words.each do |wrd|
    wd = wrd.downcase.split(//)
    sd = wd.sort.join
    aWrd.has_key?(sd) ? ((aWrd[sd] << wrd)) : (aWrd[sd] = [sd])
  end
  outAry = []
  aWrd.each { |k, v| (outAry << v) }
  return outAry
end