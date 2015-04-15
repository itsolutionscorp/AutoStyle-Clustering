class PhoneNumber

  attr_reader :area_code, :prefix, :line_number

  def initialize(input_string)
    process(input_string)
  end

  def number
    "#{area_code}#{prefix}#{line_number}"
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private
  def process(input)
    @area_code, @prefix, @line_number = case input
    when 
      /^1?\s?\(?(\d{3})\)?\s?(\d{3})-?(\d{4})$/,
      /^1?\.?(\d{3})\.(\d{3})\.(\d{4})$/ 
    then [$~[1], $~[2], $~[3]]
    else ["000", "000", "0000"]
    end
  end
end
