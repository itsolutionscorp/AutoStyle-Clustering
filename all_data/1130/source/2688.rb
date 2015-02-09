  def combine_anagrams(words)
    grouped = {}
    words.each do |word|
      k = []
      word.downcase.chars.each {|ch| k << ch}
      k = k.sort.join
      grouped[k] = [] if grouped[k].nil?
      grouped[k] << word
    end
    grouped.values
  end
