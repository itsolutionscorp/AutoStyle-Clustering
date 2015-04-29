require 'prime'

class Fixnum

  NOTES = {
    3 => ?i,
    5 => ?a,
    7 => ?o
  }

  def is_prime_factor(n)
    self % n == 0
  end

  def [](d)
    (is_prime_factor(d) ? "Pl#{NOTES[d]}ng" : '')
  end

end

class String
  def or(s)
    self == '' ? s.to_s : self
  end
end

class Raindrops
  def self.convert(n)
    (n[3] + n[5] + n[7]).or(n)
  end
end
