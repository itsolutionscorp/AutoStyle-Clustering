class Phone

  attr_reader :number

  def initialize(number)
    @number = parse_number(number.dup.gsub(/\D/, ""))
  end

  def area_code
    number[0..2]
  end

  def first_three
    number[3..5]
  end

  def last_four
    number[6..9]
  end

  def to_s
    "(#{area_code}) #{first_three}-#{last_four}"
  end

  private

    TEN_ZEROS = "0" * 10

    def parse_number(number)
      number.gsub! /\D/, ""
      number = TEN_ZEROS if number.length < 10
      if number.length > 10
        if number[0] == "1"
          number[0] = ""
        else
          number = TEN_ZEROS
        end
      end
      number
    end

end
