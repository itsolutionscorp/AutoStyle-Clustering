require 'pry'
class Cipher
  attr_accessor :key
  def initialize(key = nil)
    validate_key(key) if key
    if key
      @key = key
    else
      @key ||= ('a'..'z').to_a.join
    end
  end

  def validate_key(key)
    unless key.scan(/[a-z]/).length > 0
      raise ArgumentError
    end
  end

  def encode(str)
    str.chars.map { |el|  @key[@key.index(el) + 3] }.join
  end

  def decode(str)
    str.chars.map { |el|  @key[@key.index(el) - 3] }.join
  end
end
