require 'prime'

class Prime

  def self.nth(number)
    if number < 1
      raise ArgumentError
    else
    take(number).last
  endgi
  end

end
