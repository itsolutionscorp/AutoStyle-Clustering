def combine_anagrams(words)
  results = Array.new
  words.each do |e|
    found = 0
    results.each_index do |i|
      if (results[i].first.downcase.split(//).sort.join == e.downcase.split(//).sort.join) then
        results[i].push(e)
        found = 1
      end
    end
    results.push([e]) if (found == 0)
  end
  return results
end

