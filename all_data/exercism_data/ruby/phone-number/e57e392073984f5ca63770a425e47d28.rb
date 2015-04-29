class PhoneNumber < Struct.new(:raw_number)
  def number
    validated_number || invalid_number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{first_segment}-#{second_segment}"
  end

  def first_segment
    number[3..5]
  end

  def second_segment
    number[6..10]
  end

  private

  def validated_number
    validation_regex.match(stripped_number) do |match|
      match[1]
    end
  end

  def invalid_number
    "0000000000"
  end

  def validation_regex
    /^1?(\d{10})$/
  end

  def stripped_number
    raw_number.gsub(/[^\d]/, '')
  end
end
