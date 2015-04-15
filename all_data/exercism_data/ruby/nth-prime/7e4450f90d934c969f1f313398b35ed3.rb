require 'Prime'

class Prime
  def nth(num)
    raise(ArgumentError) if num <= 0
    (Prime.first num).pop
  end
end
