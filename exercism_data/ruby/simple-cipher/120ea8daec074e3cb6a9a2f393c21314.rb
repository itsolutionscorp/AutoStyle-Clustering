class Cipher
  attr_accessor :key

  def initialize(key)
    if key.nil?
      @key = random_key
    elsif key == "" || key.gsub(/[a-z]/,'') != ""
      raise ArgumentError
    else
      @key = key
    end
  end

  def encode(s)
    s.chars.zip(@key.chars).map do |x|
      sum = x[0].ord + x[1].ord
      sum >= 220 ? (sum - 123).chr : (sum - 97).chr
    end.join
  end

  def decode(s)
    s.chars.zip(@key.chars).map do |x|
      diff = x[0].ord - x[1].ord
      diff < 0 ? (diff + 123).chr : (diff + 97).chr
    end.join
  end

  private
  def random_key
    [*'a'..'z'].shuffle.join
  end
end
