class Phone
  attr_reader :original

  def initialize(original)
    @original = original
  end

  def number
    sanatized_number
  end

  def area_code
    sanatized_number[0,3]
  end

  def to_s
    sn = sanatized_number
    "(#{sn[0,3]}) #{sn[3,3]}-#{sn[6,4]}"
  end

  def sanatized_number
    num = original.gsub(/\D/, "")
    return num if num.length == 10
    return num[1, 10] if num.length == 11 && num[0] == "1"
    "0" * 10
  end
end
