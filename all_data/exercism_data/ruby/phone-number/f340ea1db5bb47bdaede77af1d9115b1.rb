class Phone

  attr_reader :number
  def initialize(number)
    @number = number.gsub(/\D/, "")
    @number = "0" * 10 if @number.length < 10
    if @number.length > 10
      if @number[0] == "1"
        @number[0] = ""
      else
        @number = "0" * 10
      end
    end
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3, 3]}-#{number[6,9]}"
  end

end
