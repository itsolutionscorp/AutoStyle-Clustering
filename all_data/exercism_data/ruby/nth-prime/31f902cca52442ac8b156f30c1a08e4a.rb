require 'prime'

class Prime

  def self.nth(n)
    each.with_index do |prime, index|
      return prime if index+1 == n
    end
  end

end
