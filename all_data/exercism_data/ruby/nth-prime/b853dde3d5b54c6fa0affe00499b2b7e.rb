require 'prime'

class Prime
  def self.nth number
    unless number.zero?
      Prime.take( number ).last
    else
      raise ArgumentError
    end
  end
end
