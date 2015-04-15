class Raindrops


  def self.convert(n)
    @f = []
    @h =""
    d = ((n - 1).downto(1).select do |x| (n % x).zero? end.first)
    unless d == nil
      @f << n/d
      recur(d)
    end
    @g = (@f&[3,5,7]).map do |e|
        case e
        when 3
           @h << "Pling"
        when 5
           @h << "Plang"
        else
           @h << "Plong"
        end
    end
    if @h.empty?
      n.to_s
    else
      @h
    end
  end

  def self.recur(n)
    if n == 1
      return
    else
      d = ((n-1).downto(1).select do |x| (n % x).zero? end.first)
      if d != nil && n/d != nil
        @f << n/d
      end
      recur(d)
    end
  end
end
