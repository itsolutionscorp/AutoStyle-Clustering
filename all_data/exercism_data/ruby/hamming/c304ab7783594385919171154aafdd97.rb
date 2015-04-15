module Hamming
  # variant 1: map zip reduce
  def self.compute(a, b)
    length = [ a.length, b.length ].min
    l = ->(t) { t.bytes.take(length) }
    l.(a).zip(l.(b)).map {|a,b|
      a == b ? 0 : 1
    }.reduce(0, &:+)
  end

  # variant 2: classical iterative
  def self.compute(a, b)
    sum = 0
    a.bytes.each_with_index do |byte,i|
      break if b.bytes[i].nil?
      sum += 1 if byte != b.bytes[i]
    end
    sum
  end

  # variant 3: recursive
  def self.compute(a, b)
    if a.length == 0 or b.length == 0
      0
    elsif a[0] == b[0]
      compute(a[1..-1], b[1..-1])
    else
      1 + compute(a[1..-1], b[1..-1])
    end
  end
end
