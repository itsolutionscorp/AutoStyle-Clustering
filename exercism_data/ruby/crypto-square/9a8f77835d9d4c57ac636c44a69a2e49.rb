class Crypto
  SPACE_INTERVAL = 5
  SPACE_REGEXP = /.{1,#{SPACE_INTERVAL}}/

  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.downcase.gsub(/\W+/) { "" }
  end

  def size
    s = Math.sqrt(normalize_plaintext.length)
    s % 1 == 0 ? s : s.floor + 1
  end

  def plaintext_segments
    normalize_plaintext.scan /.{1,#{size}}/
  end

  def ciphertext
    size.times.map { |i|
      plaintext_segments.map{ |segment| segment[i] }.join
    }.join
  end

  def normalize_ciphertext
    ciphertext.scan(SPACE_REGEXP).join " "
  end
end
