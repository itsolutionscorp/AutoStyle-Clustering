def combine_anagrams(words)
  o = Array.new(0)
  words.each do |ws|
    t = []
    words.each do |wa|
      t.push(wa) if (wa.downcase.chars.sort == ws.downcase.chars.sort)
    end
    o.push(t)
  end
  return o.uniq
end

