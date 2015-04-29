class PhoneNumber

  INVALID_NUM = "0000000000"
  
  def initialize(ph_num)
    @ph_num = ph_num
    @clean_num = ph_num.gsub(/[^\d]+/, '')
  end

  def number
    if @ph_num.match(/[a-z]+/)
      INVALID_NUM
    elsif @clean_num.length == 11 && @clean_num[0] == "1"
      @clean_num[1,10]
    elsif @clean_num.length != 10
      INVALID_NUM
    else
      @clean_num
    end
  end

  def area_code
    @clean_num.chars[0..2].join
  end

  def to_s
    printable = self.number
    if printable.length == 11
      printable = printable.slice[1..10]
    end

    ac = printable[0..2]
    first_3 = printable[3..5]
    last_4 = printable[6..9]
    "(" + ac + ")" + " " + first_3 + "-" + last_4
  end

end
