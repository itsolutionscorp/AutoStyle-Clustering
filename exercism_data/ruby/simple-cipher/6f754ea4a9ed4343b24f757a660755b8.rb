class Cipher
  def initialize(cipher)
    if cipher.nil?
      key
    else
      @key = cipher[/[a-z]+/] ? cipher[/[a-z]+/] : (raise ArgumentError)
    end
  end

  def key
    if @key.nil?
      @key = (1..100).map { (97 + rand(25)).chr }.join
    end
    @key
  end

  def encode(input)
    text = input.chars
    encoded = ""

    text.each_with_index do |c, i|
      distance = @key[i].ord - 97

      if c.ord + distance > 122
        encoded += (c.ord + distance - 26).chr
      else
        encoded += (c.ord + distance).chr
      end
    end

    encoded
  end

  def decode(input)
    text = input.chars
    decoded = ""

    text.each_with_index do |c, i|
      distance = @key[i].ord - 97

      if c.ord - distance < 97
        decoded += (c.ord - distance + 26).chr
      else
        decoded += (c.ord - distance).chr
      end
    end

    decoded
  end
end
