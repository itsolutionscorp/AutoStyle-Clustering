def combine_anagrams(words)
  o = Array.new(0)
  words.each { |ws|
    t = []
    words.each { |wa|
      if (wa.downcase.chars.sort == ws.downcase.chars.sort)
        t.push(wa)
      end
    }
    o.push(t)
  }
  return o.uniq
end

