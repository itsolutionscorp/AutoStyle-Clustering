class Crypto
  attr_reader :string

  def initialize(input)
    @string = input
  end

  def normalize_plaintext
    string.downcase.tr('^[a-z0-9]', '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    (0...size).each_with_object String.new do |i, result|
      plaintext_segments.each{|e| result << e[i] unless e[i].nil?}
    end
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(' ')
  end
end
