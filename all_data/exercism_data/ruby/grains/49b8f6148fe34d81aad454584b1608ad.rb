class Grains
  def square(i)
    grain = 1
    @box = []

    while grain < 999999999999999999999 do
      grain *=2
      @box << grain
    end
    @index = i-2
    @i = i
    exception
  end
  
  def exception
    if @i == 1
     1
    else
      @box[@index]
    end
  end

  def total
    square(64)
    @box.pop(7)
    @box.inject{|sum,x| sum + x } + 1
  end
end

# @box = [1,2,3,4]
# puts @box.inject{|sum,x| sum + x }



a = Grains.new
a.total
