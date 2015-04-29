class Prime
  require 'prime'
  def self.nth(n)
    if n > 0
      Prime.take(n).last
    else
      raise ArgumentError
    end
  end

end
