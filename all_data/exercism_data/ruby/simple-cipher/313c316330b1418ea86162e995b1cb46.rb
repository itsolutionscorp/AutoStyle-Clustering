class Cipher
  attr_reader :key

  def initialize(key=nil)
    if key.nil?
      @key = create_key
    else
      raise ArgumentError if key !~ /^[a-z]+$/
      @key = key
    end
  end

  def encode(s)
    res = ""
    a = 'a'.ord
    s.chars.each_with_index do |c, i|
      letterOrd = c.ord - a
      keyOrd = @key[i % @key.size].ord - a
      outOrd = ((letterOrd + keyOrd) % 26) + a
      res << outOrd.chr
    end
    res
  end

  def decode(s)
    res = ""
    a = 'a'.ord
    s.chars.each_with_index do |c, i|
      letterOrd = c.ord - a
      keyOrd = @key[i % @key.size].ord - a
      if letterOrd >= keyOrd
        outOrd = ((letterOrd - keyOrd) % 26) + a
      else
        outOrd = ((letterOrd - keyOrd) + 26) + a
      end
      res << outOrd.chr
    end
    res
  end

  private
  def create_key
    k = ""
    100.times { k << ('a'.ord + rand(26)).chr }
    k
  end
end
