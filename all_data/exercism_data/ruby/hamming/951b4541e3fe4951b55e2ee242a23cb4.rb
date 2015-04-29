class Hamming
  def self.compute(x, y)
    first = x.split("")
    second = y.split("")
    dif = 0

    first.each_with_index do |item, index|
      item != second[index] ? dif += 1 : dif
    end
    dif
  end
end
