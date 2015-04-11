require 'pry'
require 'prime'

class Prime

  def self.nth(number)
    if number > 0
      Prime.take(number).last
    else
      raise ArgumentError
    end
  end

end
