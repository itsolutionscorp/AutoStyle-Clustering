class Integer
  MAPPING = {1 => 'I', 2 => 'II', 3 =>  'III', 4 => 'IV', 5=>'V', 6=> 'VI', 7=>'VII',
    8=>'VIII', 9=>'IX', 10 => 'X', 50=> 'L', 100=> 'C', 500 => 'D', 1000 => 'M'}

  def to_roman
    if MAPPING[self]
      MAPPING[self]
    elsif self > 1000
      result = ''
      divided = self/1000
      divided.times {result += MAPPING[1000]}
      additional = hundred_to_thousand((self-(1000*divided))) unless (self-(1000*divided)).eql? 0
      result += additional unless additional.nil? || additional.empty?
      return result
    elsif self > 100
      hundred_to_thousand(self)
    elsif self > 10
      ten_to_hundred(self)
    end
  end

  private

  def hundred_to_thousand(number)
    result = ''
    divided = (number/100)

    if divided > 7
      (10-divided).times {result += MAPPING[100]}
      result += MAPPING[1000]
    elsif divided > 5
      result += MAPPING[500]
      (divided-5).times{result += MAPPING[50]}
    elsif divided > 3
      (5-divided).times {result += MAPPING[100]}
      result += MAPPING[500]
    else
      divided.times {result += MAPPING[100]}
    end

    result += ten_to_hundred(number-(100*divided)) unless (number-(100*divided)).eql? 0
  end

  def ten_to_hundred(number)
    result = ''
    divided = (number / 10)
    if divided > 7
      (10-divided).times{result += MAPPING[10]}
      result += MAPPING[100]
    elsif divided > 5
      result += MAPPING[50]
      (divided-5).times{result += MAPPING[10]}
    elsif divided > 3
      (5-divided).times{result += MAPPING[10]}
      result += MAPPING[50]
    else
      divided.times {result += MAPPING[10]}
    end

    result += MAPPING[number%10] unless (number%10).eql? 0
  end
end
