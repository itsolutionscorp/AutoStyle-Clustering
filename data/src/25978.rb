def combine_anagrams(words)
  similar = words.map do |e|
    [e.downcase.split(//).sort.inject("") { |v, d| v.concat(d) }, e]
  end
  sorted = similar.sort
  result = []
  current = nil
  previous = nil
  sorted.each do |d|
    if (previous != d[0]) then
      previous = d[0]
      result.push(current) if (current != nil)
      current = []
    end
    current.push(d[1])
  end
  result.push(current)
  return result
end