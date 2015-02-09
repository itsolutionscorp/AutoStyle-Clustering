def get_anagrams(w)
  if (w.length <= 0) then
    return
  else
    a = []
    o = []
    w.each { |e| are_anagrams(w[0], e) ? ((a << e)) : ((o << e)) }
    get_anagrams(o)
    (@anagrams << a)
    return @anagrams
  end
end

def combine_anagrams(wo)
  @anagrams = []
  get_anagrams(wo)
end

