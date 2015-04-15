class Series
  def initialize(string)
    @string = string
  end

  def slices(n)
    raise ArgumentError if n > @string.size
    output = 0.upto(@string.size - n).map{ |i| @string[i,n].chars }
    output.map{|group| group.map{|char| char.to_i}}
  end
end
