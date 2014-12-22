def combine_anagrams(words)
  groups = {}
  words.map do |x|
    { :original => (x), :sorted => (x.downcase.split("").sort.join) }
  end.each do |pair|
    groups[pair[:sorted]] ||= []
    groups[pair[:sorted]].push(pair[:original])
  end
  r = groups.values.reduce([]) { |acc, el| (acc << el) }
end

