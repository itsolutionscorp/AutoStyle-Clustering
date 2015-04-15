class Hamming

  def self.compute(a, b)
    a = a.split('')
    b = b.split('')

    counter = 0
    a.each_with_index do |x, i|
      if x != b[i]
        counter += 1
      end
    end
  return counter
  end
end
