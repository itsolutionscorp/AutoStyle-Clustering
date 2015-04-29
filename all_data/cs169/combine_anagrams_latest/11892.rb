def combine_anagrams(words)
  map = Hash.new
  words.sort!()
  words.sort_by! {|obj| obj.length }
  words.each do |word|
    if map.has_key?(word.downcase().unpack("C*").sort().pack("C*"))
      map[word.downcase().unpack("C*").sort().pack("C*")] += [word]
    else
      map[word.downcase().unpack("C*").sort().pack("C*")] = [word]
    end
  end
  arr = Array.new
  map.each_key do |key|
    arr.push(map[key])
  end
  arr
end