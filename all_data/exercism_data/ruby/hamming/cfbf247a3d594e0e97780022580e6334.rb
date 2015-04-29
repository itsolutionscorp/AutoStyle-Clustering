class Hamming

  def self.compute(a, b)
    a = a.split("")
    b = b.split("")
    cntr = [a.length, b.length].min 
    a = a[0..cntr - 1]
    b = b[0..cntr - 1]
    return (0..cntr -1).inject(0) { |enum, i| (a[i] != b[i]) ? enum + 1 : enum }
  end
end
