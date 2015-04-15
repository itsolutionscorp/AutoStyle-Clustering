require 'prime'

class Prime

  def self.nth(position)
    raise ArgumentError if position < 1
    enumerator = each
    position.times.reduce(nil) { enumerator.next }
  end
end
