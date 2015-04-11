require 'prime'
class Prime

  def self.nth(n)
    raise ArgumentError, 'Argument is not Integer' unless (n.is_a? Integer)
    raise ArgumentError, 'Argument is not a natural number' if(n < 1)

    Prime.first(n).pop
  end

end
