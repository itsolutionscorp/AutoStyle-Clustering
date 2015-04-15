class Hamming

  def self.compute(a, b)
    (0..a.length).inject(0){|s, i| s + (a[i]!=b[i]).to_i}
  end

end

class FalseClass; def to_i; 0 end end
class TrueClass; def to_i; 1 end end
