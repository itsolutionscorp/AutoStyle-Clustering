=begin
  File: prime.rb
  Author: sherinom
=end
require 'prime'

class Prime

  def self.nth(position)
    raise ArgumentError if position <= 0
    Prime.first(position)[-1]
  end

end
