class Luhn

  def self.create(number)
    if new(number).valid?
      number
    else
      modify_number_until_valid(number)
    end    
  end

  attr_reader :number, :split_numbers
  private :number, :split_numbers
  
  def initialize(number)
    @number = number
    @split_numbers = number.to_s.split("").map(&:to_i)
  end

  def addends
    split_numbers.reverse.map.with_index do |num, idx|
      if idx.odd?
        new_number = num * 2 
        ensure_number_does_not_exceed_nine(new_number)
      else
        num
      end
    end.reverse
  end

  def checksum
    addends.reduce(0, :+)
  end

  def valid?
    checksum % 10 == 0
  end

  private

    def self.modify_number_until_valid(number)
      number *= 10
      until new(number).valid?
        number += 1
      end
      number
    end

    def ensure_number_does_not_exceed_nine(new_number)
      if new_number >= 10
        new_number -= 9
      else
        new_number
      end
    end

end
