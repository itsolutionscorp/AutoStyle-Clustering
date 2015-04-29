class Phone

  def initialize(number)
    @number = number.gsub(/\D/, "")
  end

  def number
    @number = "0" * 10 if @number.length < 10
    if @number.length > 10
      if @number[0] == "1"
        @number[0] = ""
      else
        @number = "0" * 10
      end
    end
    @number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

end
