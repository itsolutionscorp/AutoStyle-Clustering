class Phone
  def initialize formatted_number
    @formatted_number = formatted_number
  end

  def number
    if cleaned_number.length == 10
      cleaned_number
    elsif cleaned_number.length == 11 and
          cleaned_number.start_with?('1')
      cleaned_number[1,10]
    else
      '0' * 10
    end
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{local_code}-#{subscriber_code}"
  end

  private

  def local_code
    number[3,3]
  end

  def subscriber_code
    number[6,4]
  end

  def cleaned_number
    @formatted_number.gsub /[^0-9]/, ''
  end
end
