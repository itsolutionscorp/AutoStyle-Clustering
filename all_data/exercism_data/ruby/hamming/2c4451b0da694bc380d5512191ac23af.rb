class Hamming

  def self.compute(a, b)

    if a.length > b.length
      hamming(b, a)
    else
      hamming(a, b)
    end
  end

  private

  def self.hamming(a, b)
    hamming = 0

    a.split("").each_with_index do |chr, index|
      hamming = hamming + 1 unless chr == b[index]
    end
    hamming
  end
end
