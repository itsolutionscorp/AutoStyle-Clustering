class Hamming
  def compute(s1, s2)
    (s1.chars.first(s2.length)).zip(s2.chars).inject(0) do |distance, chars|
      if chars[0] == chars[1]
        distance
      else
        distance + 1
      end
    end
  end
end
