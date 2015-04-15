def combine_anagrams(words)
  print("input:\n[")
  i = 0
  words.each do |w|
    print(w)
    i = (i + 1)
    w = w.downcase
    print(" , ") if (words[i] != nil)
  end
  print("]\n")
  h = Hash.new
  words.each do |w|
    print(w, " ")
    idx = w.chars.sort.join
    idx = idx.downcase
    h[idx] = Array.new if (h[idx] == nil)
    h[idx].push(w)
    h[idx].sort
  end
  ret = Array.new
  h.each do |idx, v|
    print("index:", idx, "\n")
    group = Array.new
    v.each do |val|
      print(val, " ")
      group.push(val)
    end
    print("\n")
    ret.push(group)
  end
  i = 0
  print("output:\n[")
  ret.each do |r|
    print("[")
    y = 0
    r.each do |v|
      print(v)
      y = (y + 1)
      print(" , ") if (r[y] != nil)
    end
    print("]")
    i = (i + 1)
    print(" , ") if (ret[i] != nil)
  end
  print("]\n")
  return ret
end