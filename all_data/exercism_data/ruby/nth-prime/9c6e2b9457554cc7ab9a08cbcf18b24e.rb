require 'prime'

class Prime

  def Prime.nth(n)
    
    if n < 1
      raise ArgumentError.new('illogical')
    end
    p = 0
    i = 1
    while p < n
      i += 1
      p += 1 if Prime.prime?(i)
    end
    return i
  end

end
