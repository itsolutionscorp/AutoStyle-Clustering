class Hamming
  def self.compute(first_strand, second_strand)
    useful_strands(first_strand.split(''), second_strand.split('')).
      map{|v| v[0] == v[1]}.
      select {|x| x == false}.
      count
  end

  private

  def self.useful_strands(a, b)
    maxlen = [a.length, b.length].min

    a.take(maxlen).zip(b.take(maxlen))
  end

end
