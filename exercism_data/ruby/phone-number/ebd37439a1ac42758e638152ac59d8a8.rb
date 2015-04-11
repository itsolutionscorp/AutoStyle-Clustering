class PhoneNumber
  @@bad = "0000000000"

  def initialize(s)
    if s =~ /[a-zA-Z]/
      @num = @@bad
      return
    end
    @num = s.each_char.map { |c| c if c =~ /\d/ } .join
    if @num.length == 11 and @num[0] == "1"
        @num = @num[1..10]
    elsif @num.length != 10
        @num = @@bad
    end
  end

  def number
    @num
  end

  def area_code
    @num[0..2]
  end

  def to_s
    "(#{@num[0..2]}) #{@num[3..5]}-#{@num[6..9]}"
  end
end
