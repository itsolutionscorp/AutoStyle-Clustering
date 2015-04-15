class Phone
  AREA_CODE_RANGE = (0..2)
  LOCAL_EXCHANGE_RANGE = (3..5)
  SUBSCRIBER_RANGE = (6..10)

  def initialize(written_number)
    @extractor = PhoneNumberExtractor.new(written_number)
  end

  def number
    @number ||= @extractor.extract
  end

  def area_code
    number[AREA_CODE_RANGE]
  end

  def local_number
    [number[LOCAL_EXCHANGE_RANGE],
     number[SUBSCRIBER_RANGE]].join("-")
  end

  def to_s
    "(#{area_code}) #{local_number}"
  end
end

class PhoneNumberExtractor
  TRUNK_CODE = "1" # For North American Numbering Plan
  DIGIT_LENGTH = 10
  INVALID_NUMBER = "0000000000"

  def initialize(written_number)
    @written_number = written_number
  end

  def extract
    number = @written_number
    number = clean_number(number)
    number = remove_trunk_code(number)
    number = blank_out_invalid(number)

    number
  end

  private
    def clean_number(number)
      number.scan(/\d+/).join
    end

    def remove_trunk_code(number)
      if number[0] == TRUNK_CODE and number.length > DIGIT_LENGTH 
        number[0] = ""
      end
      
      number
    end

    def blank_out_invalid(number)
      if number.length != DIGIT_LENGTH
        number = INVALID_NUMBER
      end

      number
    end
end
