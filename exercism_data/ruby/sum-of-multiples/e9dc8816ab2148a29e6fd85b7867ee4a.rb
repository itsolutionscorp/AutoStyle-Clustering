class SumOfMultiples
  def self.to(limit)
    (@@instance ||= new).to(limit)
  end

  def initialize(*factors)
    @factors = (factors.empty?) ? [3,5] : factors
  end

  def to(limit)
    (3...limit).inject(0) { |sum, current_number|
      if is_a_multiple?(current_number)
        sum + current_number
      else
        sum
      end
    }
  end

  private
  def is_a_multiple?(number)
    @factors.any?{|factor| 
      number >= factor && number % factor == 0
    }
  end
end
