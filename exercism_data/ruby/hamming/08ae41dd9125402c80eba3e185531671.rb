class Hamming
  # AGCT

  def self.compute a, b
    a.each_char.map.with_index.count do |x, index|
      x != b[index]
    end
  end
end
