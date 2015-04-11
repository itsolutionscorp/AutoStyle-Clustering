class Hamming
  def self.compute(a, b)
    if a.eql?(b)
      0
    else
      difference(a, b)
    end
  end

protected
  def self.difference(a, b)
    ret = 0
    a.split('').take(b.length).each_with_index do |letter, index|
      ret += 1 if letter != b[index]
    end
    ret
  end
end
