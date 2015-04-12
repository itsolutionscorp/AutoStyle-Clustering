class Hamming
  # AGCT

  def compute a, b
    a.each_char.map.with_index do |x, index|
      if x == b[index]
        0
      else
        1
      end
    end.inject(:+)
  end
end
