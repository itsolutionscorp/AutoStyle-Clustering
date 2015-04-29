class Luhn

  def initialize(digits)
    @digits = digits
  end

  #creates the luhn number - every other digit doubled working back from end
  def addends
    luhn_array = []
    orig_array = @digits.to_s.split(//).map(&:to_i)
    orig_array.reverse.each_with_index do |num, idx|
      if idx.odd?
        temp_num = num * 2
        if temp_num >= 10
          temp_num = temp_num - 9
        else
          temp_num
        end
        luhn_array << temp_num
      else
        luhn_array << num
      end
    end
    luhn_array.reverse
  end

  #sums luhn number
  def checksum
    addends.inject(:+)
  end

  #valid luhn nums must end in zero when summed
  def valid?
    checksum.to_s.split(//).map(&:to_i).last == 0
  end

  #given number with missing final digit, appends appropriate digit
  #to create valid luhn number
  def self.create(starting_number)
    luhn_array = []
    orig_array = starting_number.to_s.split(//).map(&:to_i).push(0)
    orig_array.reverse.each_with_index do |num, idx|
      if idx.odd?
        temp_num = num * 2
        if temp_num >= 10
          temp_num = temp_num - 9
        else
          temp_num
        end
        luhn_array << temp_num
      else
        luhn_array << num
      end
    end

    final_potential_digit = luhn_array.inject(:+).to_s.split(//).map(&:to_i).last
    if final_potential_digit == 0
      return orig_array.join.to_i
    else
      orig_array[-1] = 10 - final_potential_digit
      return orig_array.join.to_i
    end
  end

end
