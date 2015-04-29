class Hamming
  def self.compute(a, b)
    counter = 0

    a_ary = a.split("")
    b_ary = b.split("")

    a_ary.length.times do |i|
      if a[i] != b[i]
        counter += 1
      end
    end

    return counter
  end
end
