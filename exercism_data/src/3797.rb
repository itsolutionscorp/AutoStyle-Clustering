class Hamming
  def compute(s1, s2)
    if s1.length != s2.length
      short = [s1, s2].min_by &:length
      long  = [s1, s2].max_by &:length
    else
      short, long = s1, s2
    end

    short.scan(/\w/).each.with_index.count do |c1, i|
      c2 = long[i]
      c1 != c2
    end
  end
end
