class SecretHandshake

  def initialize(args)
    @binary_number = args.to_i.to_s(2)
  end

  def commands
    if @binary_number.to_i > 10_000
      return match((@binary_number.to_i-10_000).to_s)
    else
      return match(@binary_number).reverse
    end
  end

  def match(x)
    return ["wink"] if x == "1"
    return ["double blink"] if x == "10"
    return ["close your eyes"] if x == "100"
    return ["jump"] if x== "1000"
    handshake = []
    n = to_decimal(x)
    while ( n!= 0) do
        if n.to_s(2).to_i > 1000
          handshake << "jump"
        elsif n.to_s(2).to_i > 100
          handshake << "close your eyes"
        elsif n.to_s(2).to_i > 10
          handshake << "double blink"
        else
          handshake << "wink"
        end
        n = n >> 1
      end
      handshake
    end

    def to_decimal(bstr)
      return 0 unless /\D/.match(bstr) == nil #/\D/ - A non-digit character ([^0-9])
      decimal= 0
      bstr.split("").reverse.each_with_index do |n, i|
        decimal+=n.to_i*2**i
      end
      decimal
    end
  end
