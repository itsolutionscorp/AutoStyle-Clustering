class PhoneNumber

  attr_reader :number, :area_code

  def initialize(dirty_number)
    @validator = USPhoneNumberValidator.new(dirty_number)
    @number = @validator.clean_number
  end

  def area_code
    number.reverse[7..9].reverse
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private
    def prefix
      number.reverse[4..6].reverse
    end

    def line_number
      number.reverse[0..3].reverse
    end
end

class USPhoneNumberValidator

  def initialize(input)
    #@original_input = input
    @number = clean input
  end

  def clean_number
    case number.size
      when 10
        n = number
      when 11 
        n = number[1..-1] if number.start_with?('1')  
    end
    return n || '0'*10
  end

  private
    attr_reader :number

    def clean(input)
      input.delete " -.\(\)a-z"
    end
end
