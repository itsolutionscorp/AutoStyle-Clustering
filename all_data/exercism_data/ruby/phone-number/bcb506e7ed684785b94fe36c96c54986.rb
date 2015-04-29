class PhoneNumber

  attr_reader :phone_number

  def initialize(number)
    @phone_number = number
  end

  def number
    selected_number
  end

  def area_code
    number[0...3]
  end

  def to_s
    "(#{area_code}) #{number[3..-1].insert(3, '-')}"
  end

  private

  def cleaned_number
    phone_number.scan(/\d+/).join
  end

  def selected_number
    valid_number || "0000000000"
  end

  def valid_number
    if cleaned_number =~ /^\d{10}$/
      cleaned_number
    elsif cleaned_number =~ /^1\d{10}$/
      cleaned_number[1..-1]
    end
  end


end
