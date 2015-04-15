class PhoneNumber

  def initialize(d)
    @pn = "0000000000"    
    d.gsub!(/\D/, '')
    if d.length == 10
      @pn = d
    elsif d.length == 11 && d[0] == '1'
      @pn = d[1..10]
    end
  end

  def number
    @pn
  end

  def to_s
    "(" + @pn[0..2] + ") " + @pn[3..5] + "-" + @pn[6..9]
  end

  def area_code
    @pn[0..2]
  end

end
