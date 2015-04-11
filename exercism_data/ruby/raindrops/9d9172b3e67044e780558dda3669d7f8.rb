class Raindrops


  def self.convert(num)
    Raindrops.new(num).convert
  end

  attr_reader :num

  def initialize(num)
    @num = num
  end

  def convert
    retval = ""
    if @num==1
      retval += "1"
    end
    if @num%3==0
      retval += "Pling"
    end
    if @num%5==0
      retval += "Plang"
    end
    if @num%7==0
      retval += "Plong"
    end
    if retval == ""
      retval = @num.to_s
    end
    retval
  end

end
