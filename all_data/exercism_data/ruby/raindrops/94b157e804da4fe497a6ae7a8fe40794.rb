require 'prime'

class Raindrops


  def self.convert(number)

    result = factor(number)

    if Prime.prime?(result.to_i)
      result.to_s
    end

    if result.gsub(/[0-9]/,'') == ''
      number.to_s
    else
      result.gsub(/[0-9]/,'')
    end
  end

  def self.factor(number)

    case primer(number)

    when 3
      'Pling'
    when 5
      'Plang'
    when 7
      'Plong'
    else
      primer(number).to_s
    end
  end

    def self.primer(number)
      if number == 1
        number
      elsif Prime.prime?(number)
        number
      else
        ps = Prime.prime_division(number).map { |x| factor(x[0]) }.join
      end
    end
  end
