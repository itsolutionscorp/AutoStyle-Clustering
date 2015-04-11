class PhoneNumber
  def initialize(string)
    @string = string
  end

  def number
    if clean_string.size > 10 && clean_string.start_with?("11")
      clean_string.squeeze
    elsif clean_string.size > 10
      "0000000000"
    elsif clean_string.size < 10
      "0000000000"
    else
      clean_string
    end
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{split_extension}"
  end

  private

  attr_accessor :string

  def clean_string
    string.gsub(/[^0-9]/, "")
  end

  def split_extension
    extension.insert(3, "-")
  end

  def extension
    number.gsub(area_code, "")
  end
end
