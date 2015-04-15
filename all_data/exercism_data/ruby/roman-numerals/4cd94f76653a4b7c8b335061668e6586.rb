# encoding: UTF-8
class Fixnum
  RN_HASH = {
    1_000_000 => "Ṃ", 900_000 => "ĊṂ", 500_000 => "Ḍ", 400_000 => "ĊḌ", 100_000 => "Ċ",
    90_000 => "MĊ", 50_000 => "Ḷ", 40_000 => "ẊḶ", 10_000 => "Ẋ", 9000 => "MẊ", 5000 => "Ṿ",
    4000 => "MṾ", 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC",
    50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"
  }

  def to_roman
    return RN_HASH[self] if RN_HASH.has_key?(self)
    num = self
    str = ''
    RN_HASH.each_pair do |value, representation|
      while num >= value
        num -= value
        str << representation
      end
    end
    str
  end
end








# ints = self.to_s.scan(/\d/).map(&:to_i).reverse
    # value = []
    # ints.each do |int|
    #   next if int == 0
    #   less_than = RN_HASH.detect {|n, _| n < int}
    #   greater_than = RN_HASH.detect {|n, _| n > int}
    # end
    # value.join
