require 'prime'

class Prime
  def self.nth number
    unless number.zero?
      take( number ).last
    else
      raise ArgumentError
    end
  end
end
