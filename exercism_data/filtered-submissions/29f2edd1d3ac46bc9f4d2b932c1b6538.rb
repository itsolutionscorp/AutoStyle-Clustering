class Hamming
  def compute(a, b)
    counter = 0
    index = 0

    a.each_char do |a|
      if !a.eql?(b[index]) && b[index] != nil
        counter += 1
      end
      index += 1
    end

    counter
  end
end
