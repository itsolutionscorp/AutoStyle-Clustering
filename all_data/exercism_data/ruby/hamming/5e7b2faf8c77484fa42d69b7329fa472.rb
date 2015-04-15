class Hamming
  class << self
    def compute(a, b)
      a = a.split(//)
      b = b.split(//)
      count = 0
      a.each_with_index do | c, i |
        count += 1 if c != b[i] unless b[i].nil?
      end
      count
    end
  end
end
