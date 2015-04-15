require 'prime'
class Prime

  def self.nth(num)
    if num == 0
      raise ArgumentError
    end
    Prime.first(num)[num-1]
  end

end
