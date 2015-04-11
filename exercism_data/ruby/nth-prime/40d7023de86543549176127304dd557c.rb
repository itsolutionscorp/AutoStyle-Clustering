require 'prime'

class Prime

  def self.nth ordinal
    raise ArgumentError.new('Invalid argument') if ordinal < 1

    Prime.first(ordinal).last
  end

end
