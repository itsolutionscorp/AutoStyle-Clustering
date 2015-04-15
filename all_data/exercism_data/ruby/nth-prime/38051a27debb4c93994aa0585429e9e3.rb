require 'prime'

class Prime

  def self.nth(n)
    n != 0 ? Prime.first(n).last : (raise ArgumentError)
  end

end
