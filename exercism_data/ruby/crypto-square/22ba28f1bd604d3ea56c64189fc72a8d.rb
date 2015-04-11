class Crypto
  attr_reader :string
  def initialize(string)
    @string = string
  end

  def normalize_plaintext
    @string=string.gsub(/[#,$,%,^,&,,,!]/,"").delete(' ').downcase
  end

  def size
    @num = 1
    @string = Crypto.new("#{@string}").normalize_plaintext
    until @string.length <= @num*@num
      @num +=1
    end
    return @num
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end


  def chunk(s, size)
    s.scan(/.{1,#{size}}/)
  end

   def ciphertext
    plaintext_segments.map do |segment|
      segment.split('').fill('', segment.length...size)
    end.transpose.flatten.join
  end

  def normalize_ciphertext
    chunk(ciphertext, 5).join(' ')
  end
end
