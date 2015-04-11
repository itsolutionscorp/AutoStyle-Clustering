class Cipher
  # I changed the test to call it with Cipher.new instead of Cipher.new(nil)
  # I think this is a silly interface, and also created a PR for this
  # https://github.com/exercism/exercism.io/pull/1241
  def initialize key="abcdefghijklmnopqrstuvwxyz"
    raise ArgumentError unless key =~ /\A[a-z]+\z/
    @key = key
  end

  attr_reader :key

  def encode message
    message.chars.each_with_index.map do |c, i|
      plus c, key[i%key.length]
    end.join
  end

  def decode message
    message.chars.each_with_index.map do |c, i|
      minus c, key[i%key.length]
    end.join
  end

  def plus c1, c2
    c1_i = c1.ord - 'a'.ord
    c2_i = c2.ord - 'a'.ord
    res_i = (c1_i + c2_i) % 26
    ('a'.ord + res_i.ord).chr
  end

  def minus c1, c2
    c1_i = c1.ord - 'a'.ord
    c2_i = c2.ord - 'a'.ord
    res_i = (c1_i - c2_i) % 26
    ('a'.ord + res_i.ord).chr
  end
end
