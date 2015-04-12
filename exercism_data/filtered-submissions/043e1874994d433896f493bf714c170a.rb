class Hamming
  def compute(s1, s2)
    i = 0
    s1.chars.reduce(0) do |mem, char|
      mem += 1 if s2.chars[i] != char
      i += 1
      mem
    end
  end
end
