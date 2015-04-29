require 'humanize'

class Say
  def initialize(num)
    @num = num
  end

  def in_english
    validate!(@num)
    @num.humanize.gsub(/[^\w]and/, "").gsub(/,/, "")
  end

  private

  def validate!(num)
    raise ArgumentError unless num.is_a?(Fixnum) && num >= 0 && num < 1_000_000_000_000
  end
end
