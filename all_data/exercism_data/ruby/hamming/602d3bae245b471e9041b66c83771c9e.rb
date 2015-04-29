class Hamming

  def self.compute(a, b)
    if a > b
      short = b.split('')
      long = a.split('')
    else
      short = a.split('')
      long = b.split('')
    end

    count = 0
    short.each_with_index do |e, index|
      count += 1 unless e == long[index]
    end

    count
  end

end
