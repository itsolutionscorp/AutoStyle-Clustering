require 'forwardable'

class PhoneNumber
  extend Forwardable

  def initialize(text)
    @text = text
  end

  def number
    valid? ? normalized[-10..-1] : invalid
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def line_no
    number[6..10]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_no}"
  end

  private

  def normalized
    @normalized ||= text.gsub(/[^0-9]+/, '')
  end

  def valid?
    length == 10 || length == 11 && start_with?('1')
  end

  def invalid
    "0000000000"
  end

  delegate [:length, :start_with?] => :normalized

  attr_reader :text
end
