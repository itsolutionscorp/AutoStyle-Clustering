class Phone
  def initialize formatted_number
    @cleaned_number = formatted_number.delete '^0-9'
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
    format_as("(xxx) xxx-xxxx", number)
  end

  def format_as formatting, number
    format formatting.gsub('x', '%d'),
      *number.chars
  end

  attr_reader :cleaned_number
end
