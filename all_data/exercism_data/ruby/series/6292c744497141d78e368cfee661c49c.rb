class Series

  def initialize(str)
    @str = str.split("").collect {|x| x.to_i}	
  end

  def slices(amnt)
    arr = []
    raise ArgumentError if amnt > @str.size
    (0...@str.size).each do |i|
   	  arr << @str[i,amnt] if @str[i,amnt].size == amnt
    end
    arr
  end

end
