class SumOfMultiples
  def initialize(*params)
    @values = params
  end
  
  def self.to(n)
    return SumOfMultiples.new(3,5).to(n)
  end
  
  def to(n)
    values = n.times.map {0}
    @values.each do |v|
      vv = v
      while vv < n
        values[vv] = vv
        vv += v
      end
    end
    return values.inject(:+)
  end
end
