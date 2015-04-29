class PhoneNumber
  attr_reader :number

  def initialize(number)
    # Remove non-word characters and a leading 1 from an 11 character number.
    @number = number.gsub(/\W/, '').sub(/^1(?=.{10})/, '')
  end

  def valid?
    @number[/\A\d{10}\z/]
  end

  def number
    valid? || '0' * 10
  end

  def parts
    number.match(/(...)(...)(....)/)[1..-1]
  end

  def area_code
    parts.first
  end

  def to_s
    format '(%s) %s-%s', *parts
  end
end
