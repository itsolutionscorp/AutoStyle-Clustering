class Hamming
  def self.compute(a, b)
    # let's sanitize our sequences so they have common lengths  
    a.slice! b.length..a.length if a.length > b.length
    b.slice! a.length..b.length if b.length > a.length

    self.compare(a, b)
  end

  private
  def self.compare(a, b)
    diff_count = 0

    a.each_char.with_index { |c,i| diff_count += 1 unless c == b[i] }

    diff_count
  end
end
