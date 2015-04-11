class PhoneNumber
  LENGTH = 10
  DEFAULT = "0" * LENGTH

  attr_reader :area_code

  def initialize(raw_number)
    cleaned = clean(raw_number)
    @area_code, @first, @second = cleaned.scan(/(\d{3})(\d{3})(\d{4})/).flatten
  end

  def number
    components.join
  end

  def to_s
    "(%d) %d-%d" % components
  end

  private

  def components
    [@area_code, @first, @second]
  end

  def clean(number)
    number.gsub(/\W/, '').scan(/^1?(\d{#{LENGTH}})$/).flatten.first || DEFAULT
  end
end
