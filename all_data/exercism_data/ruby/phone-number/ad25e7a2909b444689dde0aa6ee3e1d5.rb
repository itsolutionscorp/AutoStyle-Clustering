class PhoneNumber

  attr_reader :number

  def initialize(str)
    @number = transform(str)
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{number[3,3]}-#{number[6,4]}"
  end

  private

  def transform str
    filtered = str.chars.select { |c| c.match(/\w/) }.join
    case
    when filtered.downcase.match(/[a-z]/) then zeros
    when filtered.length == 10 then filtered
    when filtered.length == 11 && filtered.start_with?("1") then filtered[1..-1]
    else zeros
    end
  end

  def zeros
    "0" * 10
  end

end
