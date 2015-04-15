class PhoneNumber
  attr_reader :number
  private
  def normalize(number)
    new_number=number.gsub(/([\.\(\)\s-])*/,'')
    case new_number
      when /^1\d{10}$/
        new_number[1..-1]
      when /^\d{10}$/
        new_number
      else
        '0000000000'
    end
  end

  def initialize(number)
    @number=normalize(number)
  end

  public
  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end
end
