require 'prime'

class Prime

  def self.nth(n)
    if n < 1
      raise ArgumentError.new('Try a natural number.')
    else
      Prime.first(n)[-1]
    end
  end

end
