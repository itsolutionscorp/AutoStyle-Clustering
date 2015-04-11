class PhoneNumber
  def initialize (str)
    if /[a-zA-Z]/ =~ str
      @num = "0000000000"
    else
      str = str.gsub(/[^0-9]/, "")
      if str.size == 10
        @num = str
      elsif str.size == 11 and str[0] == "1"
        @num = str[1...11]
      else
        @num = "0000000000"
      end
    end
  end
  def number
    @num
  end
  def area_code
    @num[0...3]
  end
  def to_s
    "(#{@num[0..2]}) #{@num[3..5]}-#{@num[6..10]}"
  end
end
