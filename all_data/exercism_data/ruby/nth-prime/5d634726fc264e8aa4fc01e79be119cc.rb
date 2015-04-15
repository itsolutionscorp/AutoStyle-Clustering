require 'prime'

class Prime
  def self.nth(num)
    if num > 0
      Prime.take(num)[-1]
    else
      raise ArgumentError
    end
  end
end
