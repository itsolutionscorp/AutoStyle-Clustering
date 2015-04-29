require 'prime'

class Prime

  def self.nth(num)
    if num < 1 || !num.integer?
      raise ArgumentError
    else
      Prime.take(num).last
    end
  end

end
