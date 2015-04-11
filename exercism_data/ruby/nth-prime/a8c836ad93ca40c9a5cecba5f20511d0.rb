class Prime
  require 'prime'

  def self.nth(input_value)
    unless input_value > 0
      raise ArgumentError.new("Only positive integers are allowed")
    end
    self.first(input_value)[input_value-1]
  end
end
