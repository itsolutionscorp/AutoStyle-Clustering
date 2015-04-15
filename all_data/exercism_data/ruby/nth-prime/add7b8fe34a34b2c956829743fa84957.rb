require 'prime'

class Prime
  def self.nth number
    raise ArgumentError if number.zero?
    Prime.first(number).take(number)[number - 1]
  end
end
