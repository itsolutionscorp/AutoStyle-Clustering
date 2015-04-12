class Hamming
  def compute(first,second)

    return 0 if first == second

    first.length > second.length ? limit = second.length : limit = first.length

    d = 0
    i = 0
    while d < limit do
      i+=1 if first[d] != second[d]
      d+=1
    end
    return i
  end
end
