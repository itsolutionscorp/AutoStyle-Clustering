class PhoneNumber
  def initialize(number)
    @number = parse(number)
  end

  def number
    @number || "0000000000"
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def parse(number)
    return false if number.match(/[a-z]/i)
    number = number.scan(/\d/).join

    case number.length
    when 10
      number
    when 11
      number.start_with?("1") ? number[1..10] : false
    else
      false
    end
  end
end
