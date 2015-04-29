class Cipher
  attr_reader :key
  def initialize (key = nil)
    raise ArgumentError if key and not /^[a-z]+$/ =~ key 
    @key = key || Array.new(10){|i| rand(26) + "a".ord}.map(&:chr).join
  end
  def encode (text)
    @key.chars.zip(text.chars).map do |k, v|
      ((v.ord + k.ord - 2 * "a".ord) % 26 + "a".ord).chr
    end.join
  end
  def decode (text)
    @key.chars.zip(text.chars).map do |k, v|
      ((v.ord - k.ord) % 26 + "a".ord).chr
    end.join
  end
end
