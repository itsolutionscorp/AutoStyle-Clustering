class Trinary
  attr_reader :tri
  def initialize tristr
    if tristr.scan(/[^210]/).empty?
      @tri = tristr.chars.reverse
    else
      @tri = ["0"]
    end
  end

  def to_decimal
    Array(0..@tri.length).inject do |num,i| 
      num + @tri[i-1].to_i * 3**(i-1)
    end
  end
end
