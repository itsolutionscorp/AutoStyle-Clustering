class Hamming

  def self.compute(a,b)
    a.split('').zip(b.split('')).inject(0) { |sum, e|
      sum + send("bases_matche_#{e[0] == e[1] or e[0].nil? or e[1].nil?}")
    }
  end

  def self.bases_matche_true
    0
  end

  def self.bases_matche_false
    1
  end
end