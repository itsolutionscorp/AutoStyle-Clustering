def combine_anagrams(words)
  cur = words
  out = Array.new
  while (cur.length > 0) do
    i = 0
    j = (i + 1)
    buf = [cur[i]]
    while (j < cur.length) do
      if (cur[i].downcase.split("").sort == cur[j].downcase.split("").sort) then
        (buf << cur[j])
        cur.delete_at(j)
      else
        j = (j + 1)
      end
    end
    (out << buf)
    cur.delete_at(i)
  end
  return out
end

