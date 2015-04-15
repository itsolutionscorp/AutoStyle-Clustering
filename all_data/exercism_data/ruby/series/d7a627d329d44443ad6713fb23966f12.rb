class Series
  
  def initialize(s)
    @s=s
  end  
  
  def slices(n)
    raise ArgumentError if n > @s.length
    ret = []
    (0..@s.length-1).each do |i|
      if (@s.length-i) > n-1
        v = @s.slice(i,n).split("") 
        v = v.map { |e| e.to_i}
        ret << v  
      end
    end  
    ret
  end  
  
end  
