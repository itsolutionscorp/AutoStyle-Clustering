class PhoneNumber
  NULL_NUMBER = ('0' * 10).freeze

  def initialize(string)
    @string = strip_leading_1(string.gsub(/\W/, ''))
  end

  def valid?
    @string[/\A\d{10}\z/]
  end

  def number
    valid? || NULL_NUMBER
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

  private

  def strip_leading_1(string)
    string.sub(/^1(?=.{10})/, '')
  end
end
