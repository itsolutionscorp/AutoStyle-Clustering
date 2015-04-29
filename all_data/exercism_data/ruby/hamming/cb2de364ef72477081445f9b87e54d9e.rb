class Hamming
  def self.compute(x, y)
    if x.length > y.length
      compare(y, x)
    else
      compare(x, y)
    end
  end

  def self.compare(shorter, longer)
    hamming = 0
    shorter.split('').each_with_index do |letter, i|
      hamming += 1 if letter != longer[i]
    end
    hamming
  end
end
