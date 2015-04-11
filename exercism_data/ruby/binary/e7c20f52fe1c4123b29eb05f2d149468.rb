class Binary
  attr_reader :bin
  def initialize binstr
    if binstr.scan(/[^10]/).empty?
      @bin = binstr.chars.reverse
    else
      @bin = ["0"]
    end
  end

  def to_decimal
    Array(0..@bin.length).inject do |num,i| 
      num + @bin[i-1].to_i * 2**(i-1)
    end
  end
end
