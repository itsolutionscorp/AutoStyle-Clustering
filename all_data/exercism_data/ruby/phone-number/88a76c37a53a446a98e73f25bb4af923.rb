class Phone

  attr_reader :number
  def initialize(dirty_number)
    clean_number = dirty_number.gsub(/\D/, '')
    @number = validated_number(clean_number)
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{local_prefix}-#{extension}"
  end

  private

  def validated_number(clean_number)
    if correct_digit_length(clean_number)
      clean_number
    elsif long_distance(clean_number)
      clean_number[1..-1]
    else
      "0000000000"
    end
  end

  def long_distance(clean_number)
    clean_number.length == 11 && clean_number[0] == '1'
  end

  def correct_digit_length(clean_number)
    clean_number.length == 10
  end

  def local_prefix
    number[3..5]
  end

  def extension
    number[6..9]
  end

end
