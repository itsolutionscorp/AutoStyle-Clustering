class Phone
  def initialize(input)
    @phone_no=is_valid?(input)
  end
  attr_reader :phone_no
  def number
    phone_no
  end
  def area_code
     phone_no[0..2]
  end

  def exchange_code
    phone_no[3..5]
  end

  def subscriber_number
    phone_no[6..9]
  end

  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}" #from the example illustrated in exercism
  end
  private
  def is_valid?(input)
    phoneno=input.scan(/\d+/).join
    case phoneno.length
      when 10
        phoneno
      when 11
        if phoneno.start_with?('1')
          phoneno.byteslice(1,10)
        else
          "0000000000"
        end
      else
        "0000000000"
    end
  end
end 
