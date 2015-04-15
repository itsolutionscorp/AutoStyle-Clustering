class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @normalized_plaintext ||= make_normalized_plaintext
  end

  def size
    @size ||= calc_size
  end

  def plaintext_segments
    @segments ||= make_segments
  end

  def ciphertext
    @ciphertext ||= make_ciphertext
  end

  def normalize_ciphertext
    @normalized_ciphertext ||= make_normalized_ciphertext
  end

  private

  def make_normalized_plaintext
    @plaintext.downcase.gsub(/\W+/) { "" }
  end

  def calc_size
    s = Math.sqrt(normalize_plaintext.length)
    if s % 1 == 0
      s
    else
      s.floor + 1
    end
  end

  def make_segments
    normalize_plaintext.scan Regexp.new ".{1,#{size}}"
  end

  def make_ciphertext
    size.times.reduce("") do |cipher, i|
      plaintext_segments.each do |segment|
        cipher << (segment[i] || "")
      end
      cipher
    end
  end

  def make_normalized_ciphertext
    ciphertext.scan(/.{1,5}/).join " "
  end

end
