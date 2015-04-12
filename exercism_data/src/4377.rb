class Hamming
  # AGCT

  def compute a, b
    a.each_char.with_index.count do |x, index|
      x != b[index]
    end
  end
end
