def combine_anagrams(words)
  @results = []
  word_bins = words.group_by { |word| word.length }
  word_bins.values.map do |sublist|
    sublist = sublist.zip(sublist)
    sublist.map do |wordpairs|
      wordpairs[0] = wordpairs[0].downcase.chars.sort.join
    end
    keys = sublist.collect { |list| list[0] }.uniq
    keys.each do |key|
      anagrams = sublist.collect { |list| (list[0] == key) ? (list[1]) : (nil) }
      anagrams.compact!
      (@results << anagrams)
    end
  end
  @results
end

