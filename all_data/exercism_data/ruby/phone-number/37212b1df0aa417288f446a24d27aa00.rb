class PhoneNumber

  def initialize input
    @input = input.gsub( SYMBOLS, "" )
  end

  def number
    case
      when valid_10? then input
      when valid_11? then input[1..-1]
      else                INVALID_NUMBER
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    prefix = number[3..5]
    main   = number[6..9]

    "(#{ area_code }) #{ prefix }-#{ main }"
  end

private

  INVALID_NUMBER = "0000000000"
  SYMBOLS        = /[\(\)\.\-\s]/

  attr_reader :input

  def valid_10?
    all_numbers? and input.length == 10
  end

  def valid_11?
    all_numbers? and input.length == 11 and input[0] == "1"
  end

  def all_numbers?
    @all_mumbers ||= input =~ /^\d+$/
  end

end
