class Cipher
  attr_reader :key

  def initialize(key = RandomKeyGenerator.generate)
    fail ArgumentError unless /^[a-z]+$/.match(key)
    @key = key
    @shifts = ord_array(@key).map { |el| el - 97 }
  end

  def encode(str)
    ord_array(str).zip(shifts).map do |str_ord, key_ord|
      clip_char(str_ord + key_ord)
    end.map(&:chr).join
  end

  def decode(str)
    ord_array(str).zip(shifts).map do |str_ord, key_ord|
      clip_char(str_ord - key_ord)
    end.map(&:chr).join
  end

  private

  attr_reader :shifts

  def clip_char(num)
    num -= 26 while num > 122
    num += 26 while num < 97
    num
  end

  def ord_array(str)
    str.split('').map(&:ord)
  end
end

class RandomKeyGenerator
  def self.generate(options = { max: 26, offset: 97, length: 300 })
    options[:length].times.reduce([]) do |nums, _|
      nums << rand(options[:max]) + options[:offset]
    end.shuffle.map(&:chr).join
  end
end
