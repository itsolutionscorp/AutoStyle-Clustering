class Crypto
  attr_reader :normalize_plaintext

  def initialize plaintext
    @normalize_plaintext = plaintext.scan(/\w/).join.downcase;
  end

  def plaintext_segments
    regex = Regexp.new("\\w{1," + size.to_s + "}")
    normalize_plaintext.scan(regex)
  end

  def normalize_ciphertext
    regex = Regexp.new("\\w{1," + (size).to_s + "}")
    ciphertext.scan(regex).join(' ')
  end

  def ciphertext
    segments = plaintext_segments
    result = ""

    (0...size).each do |i|
      (0...size).each do |j|
        if segments[j] && segments[j][i]
          result += segments[j][i]
        end
      end
    end

    result
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def size_1
    Math.sqrt(ciphertext.scan(/\w/).join.downcase.size).ceil
  end
end
