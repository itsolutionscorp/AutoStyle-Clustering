require 'Prime'

class Prime
  def self.nth(x)
    array = Prime.first(x)
    if x == 0
      raise ArgumentError
    else
    return array.last

  end
end

end
