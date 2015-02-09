def combine_anagrams(words)
  resultHash = Hash.new
  words.each do |x|
    keyStr=x.to_s.split(//).sort.join
    puts keyStr
    if !resultHash.key?(keyStr) then
      arr = Array.new
      arr << x
      resultHash[keyStr] = arr
    else 
      resultHash[keyStr] << x
    end
  end 
  arr2 = Array.new()
  resultHash.each_value { |value| arr2 << value }
  return arr2
end

ARGV.each do|a|
  words = eval a.downcase.gsub /\w+/, ':\&'
  p combine_anagrams(words)
end
