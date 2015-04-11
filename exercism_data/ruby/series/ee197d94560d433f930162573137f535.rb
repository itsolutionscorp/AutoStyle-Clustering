class Series
  def initialize(s)
    raise ArgumentError if s.delete('^0-9') != s
    @s = s.split("").map{|x| x.to_i}
  end
  
  def slices(n)
    result = []
    raise ArgumentError if n>@s.size
    (@s.size-n+1).times do |p|
      result << @s[p..(p+n-1)]
    end
    return result
  end
end
