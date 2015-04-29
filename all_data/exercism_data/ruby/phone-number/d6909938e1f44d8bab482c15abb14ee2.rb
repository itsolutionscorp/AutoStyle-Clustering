class PhoneNumber

  INVALID_NUMBER      = "0000000000"
  AREA_CODE_RANGE     = 0...3
  LOCAL_CODE_RANGE    = 3...6
  IDENTIFICATOR_RANGE = 6..-1

  attr_accessor :original_number

  def initialize original_number
    @original_number = original_number
  end

  def number
    @number ||= cleaned_number || INVALID_NUMBER
  end  

  def area_code
    number[ AREA_CODE_RANGE ]
  end

  def local_code
    number[ LOCAL_CODE_RANGE ]
  end

  def identificator
    number[ IDENTIFICATOR_RANGE ]
  end

  def to_s
    "(#{area_code}) #{local_code}-#{identificator}"
  end

private

  def cleaned_number
    /^1?(?<number>\d{10}$)/ =~ clean_number
    number
  end

  def clean_number
    @clean_number ||= self.original_number.gsub /\W|_/, ''
  end

end
