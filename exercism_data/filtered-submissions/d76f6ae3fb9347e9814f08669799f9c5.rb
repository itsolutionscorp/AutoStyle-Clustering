def compute(a,b)
    pairs = a.split(//).zip(b.split(//))

    pairs.map do |x|
      if x[0].nil? || x[1].nil?
        0
      elsif x[0] == x[1]
        0
      else
        1
      end
    end.reduce(:+)
  end