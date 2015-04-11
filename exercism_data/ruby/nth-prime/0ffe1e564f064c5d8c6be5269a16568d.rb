require 'prime'

class Prime
  def self.nth(n)
    if n > 0 then
      Prime.first(n).last
    else
      raise ArgumentError
    end
  end
end
