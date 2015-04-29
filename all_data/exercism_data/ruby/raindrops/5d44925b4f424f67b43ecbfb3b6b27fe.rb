class Raindrops

  def self.convert(number)
    result = ''

    if divides? 3, number
      result += 'Pling'
    end
    if divides? 5, number
      result += 'Plang'
    end
    if divides? 7, number
      result += 'Plong'
    end

    if result.empty?
      number.to_s
    else
      result
    end
  end

  private

  def self.divides?(divisor, n)
    n % divisor == 0
  end

end
