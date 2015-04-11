class Phone
  attr_reader :number

  def initialize phonenumber
    @number = uninitialized_number
    @number = clean phonenumber if valid_number? remove_non_digits phonenumber
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def uninitialized_number
    "0000000000"
  end

  def valid_number? phonenumber
    ((eleven_digits_with_area_code? phonenumber) || (ten_digits? phonenumber))
  end

  def clean phonenumber
    cleaned_number = remove_non_digits phonenumber
    if eleven_digits? cleaned_number
      cleaned_number = cleaned_number[1..10]
    end
    cleaned_number
  end

  def remove_non_digits phonenumber
    phonenumber.gsub(/\D/, "")
  end

  def eleven_digits? phonenumber
    (phonenumber.length == 11)
  end

  def ten_digits? phonenumber
    (phonenumber.length == 10)
  end

  def eleven_digits_with_area_code? phonenumber
    ((eleven_digits? phonenumber) && (phonenumber[0] == "1"))
  end

end
