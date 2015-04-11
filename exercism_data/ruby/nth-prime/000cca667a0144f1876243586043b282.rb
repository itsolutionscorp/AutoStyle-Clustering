require 'prime'

class Prime

  def self.nth(ordinal)
    raise ArgumentError if ordinal.to_i < 1
    first(ordinal.to_i).last
  end

end
