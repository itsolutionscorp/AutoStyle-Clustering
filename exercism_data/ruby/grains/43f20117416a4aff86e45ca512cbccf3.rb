class Grains
  @@b = []
  @@n = 1

  def initialize
    unless not @@b[0].nil?
      @@b = (0...64).to_a
      @@b = @@b.map { |x|
        -> {@@b[x] = @@b[x - 1] * 2}
      }
      @@b[0] = 1
    end
  end
  
  def square(n)
    for i in (@@n..n)
      begin
        @@b[i].call
      rescue
        next
      end
    end
    
    @@n = n + 1
    @@b[n - 1]
  end

  def total
    square(64)
    (@@b.last * 2) - 1 
    #@@b.inject(0, &:+)
  end
end
