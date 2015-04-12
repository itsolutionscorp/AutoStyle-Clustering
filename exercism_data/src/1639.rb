class Hamming
  def compute(s1, s2)
    distance = 0

    s1 = s1.split(//)
    s2 = s2.split(//)

    if s1.size > s2.size
      s1 = s1.slice(0, s2.size)
    end

    s1.zip(s2) do |strand1, strand2|
      if strand1 != strand2
        distance += 1
      end
    end

    distance
  end
end
