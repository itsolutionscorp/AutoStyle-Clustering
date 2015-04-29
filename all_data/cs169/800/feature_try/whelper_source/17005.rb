def combine_anagrams(words)
  temp = words.map { |w| w.downcase.split(//).sort.join }.uniq
  list = []
  temp.each do |t|
    sublist = []
    words.each { |w| (sublist << w) if (t == w.downcase.split(//).sort.join) }
    (list << sublist)
  end
  list
end

