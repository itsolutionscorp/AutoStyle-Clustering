require 'prime'

class Prime

  def self.nth(n)
    raise ArgumentError unless n > 0
    each.with_index do |prime, index|
      return prime if index+1 == n
    end
  end

end
