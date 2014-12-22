def combine_anagrams(words)
  anagrams = Hash.new {|h,k| h[k] = []}
  words.each do |w|
    anagrams[w.downcase.split('').sort.join] << w
  end
  anagrams.map{|k,v| v}
end
